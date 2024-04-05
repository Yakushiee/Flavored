package net.codenamed.flavored.block.custom;

import net.codenamed.flavored.registry.FlavoredItems;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

import java.util.stream.Stream;

public class GarlicBreadBlock extends HorizontalFacingBlock {
    public static final int MAX_BITES = 3;
    public static final IntProperty BITES;

    public static final BooleanProperty SEASONED = BooleanProperty.of("seasoned");

    public static final int DEFAULT_COMPARATOR_OUTPUT;
    protected static final float field_31047 = 1.0F;
    protected static final float field_31048 = 2.0F;
    protected static final VoxelShape[] BITES_TO_SHAPE;

    public GarlicBreadBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(BITES, 0).with(SEASONED, false));
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BITES_TO_SHAPE[(Integer)state.get(BITES)];
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (player.getStackInHand(hand).getItem() == FlavoredItems.ROSEMARY && !state.get(SEASONED)) {
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_MOSS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.setBlockState(pos, state.with(SEASONED, true));
            return ActionResult.SUCCESS;

        }

        if (world.isClient) {

            if (tryEat(world, pos, state, player).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (itemStack.isEmpty()) {
                return ActionResult.CONSUME;
            }


        }

        return tryEat(world, pos, state, player);
    }

    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            player.incrementStat(Stats.EAT_CAKE_SLICE);
            world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            player.getHungerManager().add(3, 0.5F);
            if (state.get(SEASONED)) {
                player.getHungerManager().add(1, 0.1F);

            }
            int i = (Integer)state.get(BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (i < 3) {
                world.setBlockState(pos, (BlockState)state.with(BITES, i + 1), 3);
            } else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolid();
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{BITES, SEASONED});
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getComparatorOutput((Integer)state.get(BITES));
    }

    public static int getComparatorOutput(int bites) {
        return (4 - bites) * 2;
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        BITES = Properties.BITES;
        DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
        BITES_TO_SHAPE = new VoxelShape[]{
                Stream.of(
                        Block.createCuboidShape(3, 0, 8, 8, 3, 13),
                        Block.createCuboidShape(8, 0, 3, 13, 3, 8),
                        Block.createCuboidShape(8, 0, 8, 13, 3, 13),
                        Block.createCuboidShape(3, 0, 3, 8, 3, 8)
                ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get(),
        Stream.of(
                Block.createCuboidShape(3, 0, 8, 8, 3, 13),
                Block.createCuboidShape(8, 0, 3, 13, 3, 8),
                Block.createCuboidShape(8, 0, 8, 13, 3, 13)
        ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get(),

                VoxelShapes.combineAndSimplify(Block.createCuboidShape(3, 0, 8, 8, 3, 13),
                        Block.createCuboidShape(8, 0, 8, 13, 3, 13), BooleanBiFunction.OR),


                Block.createCuboidShape(8, 0, 8, 13, 3, 13),
                Block.createCuboidShape(8, 0, 8, 13, 3, 13),
                Block.createCuboidShape(0, 0, 0, 0, 0, 0),
                Block.createCuboidShape(0, 0, 0, 0, 0, 0)};
    }
}
