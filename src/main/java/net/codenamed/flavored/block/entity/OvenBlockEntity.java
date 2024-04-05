package net.codenamed.flavored.block.entity;

import net.codenamed.flavored.block.custom.FermenterBlock;
import net.codenamed.flavored.block.custom.OvenBlock;
import net.codenamed.flavored.registry.FlavoredBlockEntities;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.codenamed.flavored.recipe.OvenRecipe;
import net.codenamed.flavored.screen.OvenScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class OvenBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private static final int FUEL_SLOT = 0;

    private static final int INPUT_SLOT1 = 1;

    private static final int INPUT_SLOT2 = 2;

    private static final int INPUT_SLOT3 = 3;

    private static final int INPUT_SLOT4 = 4;

    private static final int OUTPUT_SLOT = 5;
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 160;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(6, ItemStack.EMPTY);

    public OvenBlockEntity(BlockPos pos, BlockState state) {
        super(FlavoredBlockEntities.OVEN_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return OvenBlockEntity.this.progress;
                    case 1:
                        return OvenBlockEntity.this.maxProgress;
                    case 2:
                        return OvenBlockEntity.this.fuelTime;
                    case 3:
                        return OvenBlockEntity.this.maxFuelTime;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        OvenBlockEntity.this.progress = value;
                        break;
                    case 1:
                        OvenBlockEntity.this.maxProgress = value;
                        break;
                    case 2:
                        OvenBlockEntity.this.fuelTime = value;
                        break;
                    case 3:
                        OvenBlockEntity.this.maxFuelTime = value;
                        break;
                }
            }

            public int size() {
                return 6;
            }

        };
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Oven");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("oven.progress", progress);
        nbt.putInt("oven.fuelTime", fuelTime);
        nbt.putInt("oven.maxFuelTime", maxFuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("oven.progress");
        fuelTime = nbt.getInt("oven.fuelTime");
        maxFuelTime = nbt.getInt("oven.maxFuelTime");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new OvenScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }


    public void tick(World world, BlockPos pos, BlockState state, OvenBlockEntity entity) {
        if(isConsumingFuel(entity)) {
            entity.fuelTime--;
            state = (BlockState)state.with(OvenBlock.LIT, isConsumingFuel(entity));
            world.setBlockState(pos, state, 3);

        }
        if(hasRecipe()) {
            if(hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {

                entity.consumeFuel();
            }
            if(isConsumingFuel(entity)) {
                entity.progress++;
                if(entity.progress > entity.maxProgress) {
                    craftItem();
                    entity.resetProgress();
                }
            }
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasFuelInFuelSlot(OvenBlockEntity entity) {
        return !entity.getStack(FUEL_SLOT).isEmpty();
    }

    private static boolean isConsumingFuel(OvenBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<RecipeEntry<OvenRecipe>> recipe = getCurrentRecipe();

        if (this.getStack(INPUT_SLOT1) == Items.WATER_BUCKET.getDefaultStack() || this.getStack(INPUT_SLOT1) == Items.MILK_BUCKET.getDefaultStack()) {
            this.removeStack(INPUT_SLOT1, 1);
            this.setStack(INPUT_SLOT1, Items.BUCKET.getDefaultStack());
        }
        else {
            this.removeStack(INPUT_SLOT1, 1);
        }

        if (this.getStack(INPUT_SLOT2) == Items.WATER_BUCKET.getDefaultStack() || this.getStack(INPUT_SLOT2) == Items.MILK_BUCKET.getDefaultStack()) {
            this.removeStack(INPUT_SLOT2, 1);
            this.setStack(INPUT_SLOT2, Items.BUCKET.getDefaultStack());
        }
        else {
            this.removeStack(INPUT_SLOT2, 1);
        }
        if (this.getStack(INPUT_SLOT3) == Items.WATER_BUCKET.getDefaultStack() || this.getStack(INPUT_SLOT3) == Items.MILK_BUCKET.getDefaultStack()) {
            this.removeStack(INPUT_SLOT3, 1);
            this.setStack(INPUT_SLOT3, Items.BUCKET.getDefaultStack());
        }
        else {
            this.removeStack(INPUT_SLOT3, 1);
        }

        if (this.getStack(INPUT_SLOT4) == Items.WATER_BUCKET.getDefaultStack() || this.getStack(INPUT_SLOT4) == Items.MILK_BUCKET.getDefaultStack()) {
            this.removeStack(INPUT_SLOT4, 1);
            this.setStack(INPUT_SLOT4, Items.BUCKET.getDefaultStack());
        }
        else {
            this.removeStack(INPUT_SLOT4, 1);
        }




        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress ++;
    }

    private void consumeFuel() {
        if(!getStack(0).isEmpty()) {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            this.maxFuelTime = this.fuelTime;
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<OvenRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null))
                && canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private Optional<RecipeEntry<OvenRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(OvenRecipe.Type.INSTANCE, inv, getWorld());

    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

}
