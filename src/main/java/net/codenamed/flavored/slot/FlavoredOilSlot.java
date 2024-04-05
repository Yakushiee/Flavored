package net.codenamed.flavored.slot;

import net.codenamed.flavored.Flavored;
import net.codenamed.flavored.registry.FlavoredItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class FlavoredOilSlot extends Slot {
    public FlavoredOilSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return FlavoredOilSlot.isOil(stack);
    }


    public static boolean isOil(ItemStack stack) {
        return stack.isOf(FlavoredItems.OIL);
    }
}