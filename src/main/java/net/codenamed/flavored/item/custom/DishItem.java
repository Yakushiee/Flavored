package net.codenamed.flavored.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class DishItem extends Item {

    public DishItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {

        if (user instanceof PlayerEntity) {
            int count = 0;
            for (int i = 0; i < ((PlayerEntity) user).getInventory().size(); i++) {
                count++;
                if (((PlayerEntity) user).getInventory().getStack(i) == ItemStack.EMPTY && count == ((PlayerEntity) user).getInventory().size()) {

                    user.dropStack(Items.BOWL.getDefaultStack());
                }

            }
            ((PlayerEntity) user).getInventory().insertStack(Items.BOWL.getDefaultStack());
        }
        return this.isFood() ? user.eatFood(world, stack) : stack;

    }


}
