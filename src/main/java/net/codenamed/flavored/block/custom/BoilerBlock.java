package net.codenamed.flavored.block.custom;

import net.codenamed.flavored.block.entity.FermenterBlockEntity;
import net.codenamed.flavored.block.entity.OvenBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.codenamed.flavored.block.entity.BoilerBlockEntity;
import net.codenamed.flavored.registry.FlavoredBlockEntities;
import net.codenamed.flavored.block.entity.RangeBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class BoilerBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public static final IntProperty WATER = IntProperty.of("water", 0, 3);

    public  static  final  int MAX_WATER = 3;


    public BoilerBlock(Settings settings) {
        super(settings);
    }

    private static VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(2, 0, 2, 14, 6, 3),
            Block.createCuboidShape(3, 0, 3, 13, 1, 13),
            Block.createCuboidShape(2, 0, 13, 14, 6, 14),
            Block.createCuboidShape(2, 0, 3, 3, 6, 13),
            Block.createCuboidShape(13, 0, 3, 14, 6, 13)
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, WATER});

    }


    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BoilerBlockEntity) {
                ItemScatterer.spawn(world, pos, (BoilerBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, Hand hand, BlockHitResult hit) {
        BlockEntity b = world.getBlockEntity(pos);


        if (player.getStackInHand(hand).getItem() == Items.WATER_BUCKET  && state.get(WATER) < MAX_WATER  || player.getStackInHand(hand).getItem() == Items.POTION && state.get(WATER) < MAX_WATER ) {
            world.setBlockState(pos, state.with(WATER, state.get(WATER) + 1));
            world.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 0.2F, 1.5F);

            if (b instanceof BoilerBlockEntity) {
                ((BoilerBlockEntity) b).setLiquid(state.get(WATER) + 1);

                return ActionResult.SUCCESS;

            }

        }

        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((BoilerBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
                return ActionResult.SUCCESS;

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
        return new BoilerBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, FlavoredBlockEntities.BOILER_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }


}