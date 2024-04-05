package net.codenamed.flavored.block.custom;

import net.codenamed.flavored.block.entity.OvenBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.codenamed.flavored.registry.FlavoredBlocks;
import net.codenamed.flavored.registry.FlavoredItems;
import net.minecraft.world.WorldAccess;

import java.util.*;
import java.util.stream.Stream;

public class CrateBlock extends Block {


    public static final BooleanProperty EMPTY = BooleanProperty.of("empty");
    public  static  final IntProperty CROP = IntProperty.of("crop", 0, 11);
    public  static  final IntProperty AMOUNT = IntProperty.of("amount", 0, 9);




    public static final ArrayList<Item> crops = new ArrayList<>();
    public static final ArrayList<Block> full_crates = new ArrayList<>();


    public  void registerCrops() {

        crops.add(FlavoredItems.TOMATO);
        crops.add(Items.APPLE);
        crops.add(Items.CARROT);
        crops.add(Items.BEETROOT);
        crops.add(Items.POTATO);
        crops.add(Items.SWEET_BERRIES);
        crops.add(Items.GLOW_BERRIES);
        crops.add(Items.CHORUS_FRUIT);
        crops.add(FlavoredItems.GARLIC);
        crops.add(FlavoredItems.ROSEMARY);
        crops.add(FlavoredItems.FIG);
        crops.add(FlavoredItems.SPINACH);



    }

    public CrateBlock(boolean empty, Item crop, Settings settings) {
        super(settings);
        if (empty) {
            this.setDefaultState((this.stateManager.getDefaultState()).with(EMPTY, true));
        }
        else {
            for (int i = 0; i < crops.size(); i++ ) {
                if (crops.get(i) == crop) {
                    this.setDefaultState((this.stateManager.getDefaultState()).with(EMPTY, false).with(CROP, i).with(AMOUNT, MAX_AMOUNT));
                }
            }

        }
        if (empty) {
            registerCrops();
        }

    }

    public  static VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 16, 2),
            Block.createCuboidShape(0, 0, 14, 16, 16, 16),
            Block.createCuboidShape(2, 0, 2, 14, 2, 14),
            Block.createCuboidShape(0, 0, 2, 2, 16, 14),
            Block.createCuboidShape(14, 0, 2, 16, 16, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(EMPTY)) {
            return SHAPE;
        }
        else {
            return super.getOutlineShape(state, world, pos, context);
        }
    }



    public  static  final int MAX_AMOUNT = 9;

    protected ItemConvertible getCropItem(BlockState state) {
        if (!state.get(EMPTY)) {
            return crops.get(state.get(CROP));
        }
        return FlavoredBlocks.CRATE;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(this.getCropItem(state));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {


            if (state.get(EMPTY)) {
                for (int i = 0; i < crops.size(); i++) {
                    if (player.getStackInHand(hand).getItem() == crops.get(i)) {
                        world.setBlockState(pos, (BlockState) state.with(EMPTY, false).with(CROP, i).with(AMOUNT, state.get(AMOUNT)+1));
                        world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_COMPOSTER_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        player.getStackInHand(hand).decrement(1);

                        break;
                    }
                }

                return ActionResult.SUCCESS;

            }
            else if (!state.get(EMPTY) && state.get(AMOUNT) < MAX_AMOUNT && player.getStackInHand(hand).getItem() == crops.get(state.get(CROP))) {

                world.setBlockState(pos, (BlockState) state.with(EMPTY, false).with(CROP, state.get(CROP)).with(AMOUNT, state.get(AMOUNT) + 1));
                world.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_COMPOSTER_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.getStackInHand(hand).decrement(1);



                return ActionResult.SUCCESS;
            }





            else if (player.getStackInHand(hand).isEmpty() && !state.get(EMPTY)) {
                player.getInventory().insertStack(crops.get(state.get(CROP)).getDefaultStack());
                world.playSound((PlayerEntity)null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 0.2F, 1.5F);
                if ((state.get(AMOUNT)-1) == 0) {
                    world.setBlockState(pos, (BlockState) state.with(EMPTY, true).with(CROP, state.get(CROP)).with(AMOUNT, 0));

                }
                else {
                    world.setBlockState(pos, (BlockState) state.with(EMPTY, false).with(CROP, state.get(CROP)).with(AMOUNT, state.get(AMOUNT)-1));
                    if (state.get(AMOUNT) == MAX_AMOUNT-1) {
                        world.setBlockState(pos, (BlockState) FlavoredBlocks.CRATE.getDefaultState().with(CROP, state.get(CROP)).with(AMOUNT, 7).with(EMPTY, false));

                    }
                }




               return ActionResult.SUCCESS;
            }






            return ActionResult.FAIL;
    }


    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(AMOUNT) <= MAX_AMOUNT-1) {
            ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(crops.get(state.get(CROP)), state.get(AMOUNT)));

        }
        super.onBreak(world, pos, state, player);

    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {



        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        world.setBlockState(pos, (BlockState) state.with(EMPTY, state.get(EMPTY)).with(CROP, state.get(CROP)).with(AMOUNT, state.get(AMOUNT)));

        super.randomDisplayTick(state, world, pos, random);
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return (int)Math.ceil(state.get(AMOUNT) / 9);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AMOUNT, EMPTY, CROP});
    }
}
