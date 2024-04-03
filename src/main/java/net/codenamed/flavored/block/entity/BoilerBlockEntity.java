package net.codenamed.flavored.block.entity;

import net.codenamed.flavored.block.custom.BoilerBlock;
import net.codenamed.flavored.block.custom.FermenterBlock;
import net.codenamed.flavored.recipe.FermenterRecipe;
import net.codenamed.flavored.recipe.OvenRecipe;
import net.codenamed.flavored.registry.FlavoredBlockEntities;
import net.codenamed.flavored.screen.BoilerScreenHandler;
import net.codenamed.flavored.screen.FermenterScreenHandler;
import net.codenamed.flavored.screen.OvenScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
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
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.codenamed.flavored.recipe.BoilerRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BoilerBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(6, ItemStack.EMPTY);

    private static final int INPUT_SLOT1 = 0;
    private static final int INPUT_SLOT2 = 1;
    private static final int INPUT_SLOT3 = 2;
    private static final int INPUT_SLOT4 = 3;

    private static final int BOWL_SLOT = 4;

    private static final int OUTPUT_SLOT = 5;




    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private  int water = 0;

    private int maxProgress = 120;

    public BoilerBlockEntity(BlockPos pos, BlockState state) {
        super(FlavoredBlockEntities.BOILER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BoilerBlockEntity.this.progress;
                    case 1 -> BoilerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BoilerBlockEntity.this.progress = value;
                    case 1 -> BoilerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 6;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Boiler");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public  void  setLiquid(int l) {
        this.water = l;
    }

    public  int  getLiquid() {

        return water;
    }
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("boiler.progress", progress);
        nbt.putInt("boiler.water", water);

    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("boiler.progress");
        water = nbt.getInt("boiler.water");
        System.out.println(water);

    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BoilerScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }
        world.setBlockState(pos, state);

        if(isOutputSlotEmptyOrReceivable()) {
            if(this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if(hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                    state = (BlockState)state.with(BoilerBlock.WATER, water);
                    world.setBlockState(pos, state);

                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<RecipeEntry<BoilerRecipe>> recipe = getCurrentRecipe();

        this.removeStack(INPUT_SLOT1, 1);
        this.removeStack(INPUT_SLOT2, 1);
        this.removeStack(INPUT_SLOT3, 1);
        this.removeStack(INPUT_SLOT4, 1);

        this.removeStack(BOWL_SLOT, 1);

        this.water--;





        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().getResult(null).getItem(),
                getStack(OUTPUT_SLOT).getCount() + recipe.get().value().getResult(null).getCount()));
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress += 1;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<BoilerRecipe>> recipe = getCurrentRecipe();

        return this.water > 0 && this.getStack(BOWL_SLOT).getItem() == Items.BOWL && recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().getResult(null))
                && canInsertItemIntoOutputSlot(recipe.get().value().getResult(null).getItem());
    }

    private Optional<RecipeEntry<BoilerRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return getWorld().getRecipeManager().getFirstMatch(BoilerRecipe.Type.INSTANCE, inv, getWorld());

    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }
}
