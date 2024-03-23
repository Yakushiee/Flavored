package net.codenamed.flavored.block.entity;

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
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.codenamed.flavored.item.FlavoredItems;
import net.codenamed.flavored.recipe.RangeRecipe;
import net.codenamed.flavored.screen.RangeScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RangeBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private static final int FUEL_SLOT = 0;

    private static final int OIL_SLOT = 1;


    private static final int INPUT_SLOT1 = 2;

    private static final int INPUT_SLOT2 = 3;

    private static final int INPUT_SLOT3 = 4;

    private static final int INPUT_SLOT4 = 5;

    private static final int OUTPUT_SLOT = 6;
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 144;
    private int fuelTime = 0;
    private int maxFuelTime = 0;
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(7, ItemStack.EMPTY);

    public RangeBlockEntity(BlockPos pos, BlockState state) {
        super(FlavoredBlockEntities.RANGE_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return RangeBlockEntity.this.progress;
                    case 1:
                        return RangeBlockEntity.this.maxProgress;
                    case 2:
                        return RangeBlockEntity.this.fuelTime;
                    case 3:
                        return RangeBlockEntity.this.maxFuelTime;
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        RangeBlockEntity.this.progress = value;
                        break;
                    case 1:
                        RangeBlockEntity.this.maxProgress = value;
                        break;
                    case 2:
                        RangeBlockEntity.this.fuelTime = value;
                        break;
                    case 3:
                        RangeBlockEntity.this.maxFuelTime = value;
                        break;
                }
            }

            public int size() {
                return 7;
            }
        };
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Range");
    }

    public  boolean getOil() {
        return inventory.get(OIL_SLOT) == FlavoredItems.OIL.getDefaultStack();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("range.progress", progress);
        nbt.putInt("range.fuelTime", fuelTime);
        nbt.putInt("range.maxFuelTime", maxFuelTime);
    }

    public  ItemStack getRenderStack(int index) {

        if (!this.getStack(6).isEmpty()) {
            return  getStack(6);
        }
        else {
            if (this.getStack(index).isEmpty()) {
                return ItemStack.EMPTY;

            } else {
                return this.getStack(index);

            }
        }


    }

    public  ItemStack getRenderStack() {

        if (!this.getStack(6).isEmpty()) {
            return  getStack(6);
        }
        return ItemStack.EMPTY;


    }


    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        for (int i = 0; i < inventory.size(); i++) {
            this.inventory.set(i, ItemStack.EMPTY);
        }
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("range.progress");
        progress = nbt.getInt("range.fuelTime");
        progress = nbt.getInt("range.maxFuelTime");


    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new RangeScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state, RangeBlockEntity entity) {
        if(isConsumingFuel(entity)) {
            entity.fuelTime--;
        }

        if(hasRecipe()) {
            if(hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                entity.consumeFuel();
            }
            if(isConsumingFuel(entity)) {
                entity.progress++;
                if(entity.progress > entity.maxProgress) {
                    craftItem();
                }
            }
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasFuelInFuelSlot(RangeBlockEntity entity) {
        return !entity.getStack(FUEL_SLOT).isEmpty();
    }

    private static boolean isConsumingFuel(RangeBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    public boolean getFuel(RangeBlockEntity r) {
        return  isConsumingFuel(r);
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<RecipeEntry<RangeRecipe>> recipe = getCurrentRecipe();

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

            this.removeStack(OIL_SLOT, 1);





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
        if(!getStack(FUEL_SLOT).isEmpty()) {
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(FUEL_SLOT, 1).getItem());
            this.maxFuelTime = this.fuelTime;
        }
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<RangeRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null))
                && canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private Optional<RecipeEntry<RangeRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(RangeRecipe.Type.INSTANCE, inv, getWorld());

    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}