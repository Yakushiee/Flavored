package net.codenamed.flavored.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.codenamed.flavored.registry.FlavoredBlockEntities;
import net.codenamed.flavored.block.entity.RangeBlockEntity;
import net.codenamed.flavored.registry.FlavoredItems;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class RangeBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public  static BooleanProperty OIL = BooleanProperty.of("oil");
    public  static BooleanProperty LIT = BooleanProperty.of("lit");


    public RangeBlock(Settings settings) {
        super(settings);
        this.setDefaultState((this.stateManager.getDefaultState()).with(OIL, false).with(LIT, false));

    }

    private static VoxelShape SHAPE = Stream.of(
                    Block.createCuboidShape(0, 5, 0, 16, 16, 16),
                    Block.createCuboidShape(1, 3, 1, 15, 5, 15),
                    Block.createCuboidShape(1, 0, 12, 4, 3, 15),
                    Block.createCuboidShape(12, 0, 12, 15, 3, 15),
                    Block.createCuboidShape(1, 0, 1, 4, 3, 4),
                    Block.createCuboidShape(12, 0, 1, 15, 3, 4)
            ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }



    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, LIT, OIL});
    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof RangeBlockEntity) {
                ItemScatterer.spawn(world, pos, (RangeBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity b = world.getBlockEntity(pos);

        if (player.getStackInHand(hand).getItem() == FlavoredItems.OIL) {
            player.getStackInHand(hand).decrement(1);
            world.setBlockState(pos, state.with(OIL, true));

            if (b instanceof RangeBlockEntity) {

                if (((RangeBlockEntity) b).getStack(1).getItem() == FlavoredItems.OIL.asItem()) {
                    ((RangeBlockEntity) b).getStack(1).increment(1);

                }
                else {
                    ((RangeBlockEntity) b).setStack(1, FlavoredItems.OIL.getDefaultStack());
                }
                return ActionResult.SUCCESS;

            }
        }

        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }


        return ActionResult.SUCCESS;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return super.getComparatorOutput(state, world, pos);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RangeBlockEntity(pos, state);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        BlockEntity b = world.getBlockEntity(pos);

        if (state.get(LIT)) {
            double d = (double) pos.getX() + 0.5;
            double e = (double) pos.getY() + 1;
            double f = (double) pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                world.playSound(d, e, f, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.1F, 1.5F, false);
            }

            Direction direction = (Direction) state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52;
            double h = random.nextDouble() * 0.6 - 0.3;
            double i = axis == Direction.Axis.X ? (double) direction.getOffsetX() * 0.52 : h;
            double j = random.nextDouble() * 6.0 / 16.0;
            double k = axis == Direction.Axis.Z ? (double) direction.getOffsetZ() * 0.52 : h;
            world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0, 0.0, 0.0);

            super.randomDisplayTick(state, world, pos, random);
        }
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, FlavoredBlockEntities.RANGE_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1, blockEntity));
    }


}