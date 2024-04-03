package net.codenamed.flavored.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.codenamed.flavored.Flavored;

public class FlavoredItemGroup {
    public static ItemGroup FLAVORED_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(Flavored.MOD_ID, "flavored_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.flavored_group"))
                    .icon(() -> new ItemStack(FlavoredItems.TOMATO)).entries((displayContext, entries) -> {


                        entries.add(FlavoredBlocks.ANCIENT_LOG);
                        entries.add(FlavoredBlocks.ANCIENT_WOOD);
                        entries.add(FlavoredBlocks.STRIPPED_ANCIENT_LOG);
                        entries.add(FlavoredBlocks.STRIPPED_ANCIENT_WOOD);
                        entries.add(FlavoredBlocks.ANCIENT_PLANKS);
                        entries.add(FlavoredBlocks.ANCIENT_STAIRS);
                        entries.add(FlavoredBlocks.ANCIENT_SLAB);
                        entries.add(FlavoredBlocks.ANCIENT_FENCE);
                        entries.add(FlavoredBlocks.ANCIENT_FENCE_GATE);
                        entries.add(FlavoredBlocks.ANCIENT_BUTTON);
                        entries.add(FlavoredBlocks.ANCIENT_PRESSURE_PLATE);
                        entries.add(FlavoredBlocks.ANCIENT_DOOR);
                        entries.add(FlavoredBlocks.ANCIENT_TRAPDOOR);
                        entries.add(FlavoredItems.HANGING_ANCIENT_SIGN);
                        entries.add(FlavoredItems.ANCIENT_SIGN);
                        entries.add(FlavoredItems.ANCIENT_BOAT);
                        entries.add(FlavoredItems.ANCIENT_CHEST_BOAT);
                        entries.add(FlavoredBlocks.ANCIENT_LEAVES);
                        entries.add(FlavoredBlocks.FLOWERING_ANCIENT_LEAVES);
                        entries.add(FlavoredBlocks.ANCIENT_SAPLING);
                        entries.add(FlavoredBlocks.CRATE);
                        entries.add(FlavoredBlocks.PLANT_POT);
                        entries.add(FlavoredBlocks.FERMENTER);
                        entries.add(FlavoredBlocks.OVEN);
                        entries.add(FlavoredBlocks.RANGE);
                        entries.add(FlavoredBlocks.BOILER);
                        entries.add(FlavoredItems.FLOUR);
                        entries.add(FlavoredBlocks.CHEESE);
                        entries.add(FlavoredItems.CHEESE_SLICE);
                        entries.add(FlavoredItems.OIL);
                        entries.add(FlavoredItems.TOMATO);
                        entries.add(FlavoredItems.TOMATO_SEEDS);
                        entries.add(FlavoredItems.GARLIC);
                        entries.add(FlavoredBlocks.CAULIFLOWER);
                        entries.add(FlavoredBlocks.CARVED_CAULIFLOWER);
                        entries.add(FlavoredItems.CAULIFLOWER_CURD);
                        entries.add(FlavoredItems.CAULIFLOWER_SEEDS);
                        entries.add(FlavoredItems.SPINACH);
                        entries.add(FlavoredItems.SPINACH_SEEDS);
                        entries.add(FlavoredItems.FIG);
                        entries.add(FlavoredItems.ROSEMARY);
                        entries.add(FlavoredItems.PUMPKIN_SLICE);
                        entries.add(FlavoredBlocks.CARVED_MELON);
                        entries.add(FlavoredItems.PASTA);
                        entries.add(FlavoredItems.ROASTED_CAULIFLOWER_CURD);
                        entries.add(FlavoredItems.CAULIFLOWER_SOUP);
                        entries.add(FlavoredItems.SPAGHETTI);
                        entries.add(FlavoredItems.CARBONARA);
                        entries.add(FlavoredItems.SALAD);
                        entries.add(FlavoredItems.SWEET_BERRY_MUFFIN);
                        entries.add(FlavoredItems.GLOW_BERRY_MUFFIN);
                        entries.add(FlavoredItems.GLAZED_BEEF);
                        entries.add(FlavoredItems.GLAZED_PORK);
                        entries.add(FlavoredItems.GLAZED_MUTTON);
                        entries.add(FlavoredItems.GLAZED_CHICKEN);
                        entries.add(FlavoredItems.CANDIED_APPLE);
                        entries.add(FlavoredItems.ALFREDO);
                        entries.add(FlavoredItems.OCEAN_STEW);
                        entries.add(FlavoredItems.GRILLED_VEGETABLES);
                        entries.add(FlavoredItems.FRUIT_SALAD);
                        entries.add(FlavoredItems.PORRIDGE);
                        entries.add(FlavoredItems.SHAKSHOKA);
                        entries.add(FlavoredItems.MINESTRONE);
                        entries.add(FlavoredBlocks.PIZZA);
                        entries.add(FlavoredItems.PIZZA_SLICE);
                        entries.add(FlavoredBlocks.GARLIC_BREAD);
                        entries.add(FlavoredBlocks.PUDDING);
                        entries.add(FlavoredItems.CIDER);
                        entries.add(FlavoredItems.SWEET_BERRY_COCKTAIL);
                        entries.add(FlavoredItems.GLOW_BERRY_COCKTAIL);
                        entries.add(FlavoredItems.CHORUS_COCKTAIL);
                        entries.add(FlavoredItems.FIG_COCKTAIL);
                        entries.add(FlavoredItems.MALT);
                        entries.add(FlavoredItems.HOT_COCOA);
                        entries.add(FlavoredItems.PUMPKIN_LATTE);
                        entries.add(FlavoredItems.TEA);
                        entries.add(FlavoredItems.DELICACY_POTTERY_SHERD);
                        entries.add(FlavoredItems.REFRESH_POTTERY_SHERD);











                    }).build());

    public static void registerItemGroups() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

        });
    }
}