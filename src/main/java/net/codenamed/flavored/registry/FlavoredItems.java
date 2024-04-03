package net.codenamed.flavored.registry;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.codenamed.flavored.Flavored;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.codenamed.flavored.item.custom.*;

public class FlavoredItems {

    public static final Item TOMATO = registerItem("tomato",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.TOMATO)));

    public static final Item TOMATO_SEEDS = registerItem("tomato_seeds",
            new TomatoSeedItem(FlavoredBlocks.TOMATO_CROP, new FabricItemSettings()));

    public static final Item GARLIC = registerItem("garlic",
            new AliasedBlockItem(FlavoredBlocks.GARLICS, (new FabricItemSettings()).food(FlavoredFoodComponents.GARLIC)));

    public static final Item CAULIFLOWER_CURD = registerItem("cauliflower_curd",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.CAULIFLOWER_CURD)));

    public static final Item PUMPKIN_SLICE = registerItem("pumpkin_slice",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.PUMPKIN_SLICE)));

    public static final Item FIG = registerItem("fig",
            new AliasedBlockItem(FlavoredBlocks.FIG, (new FabricItemSettings()).food(FlavoredFoodComponents.FIG)));

    public static final Item FLOUR = registerItem("flour",
            new Item(new FabricItemSettings()));

    public static final Item ROSEMARY = registerItem("rosemary",
            new AliasedBlockItem(FlavoredBlocks.ROSEMARY_BUSH, new FabricItemSettings()));

    public static final Item OIL = registerItem("oil",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.OIL)));

    public static final Item PASTA = registerItem("pasta",
            new Item(new FabricItemSettings()));

    public static final Item ROASTED_CAULIFLOWER_CURD = registerItem("roasted_cauliflower_curd",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.ROASTED_CAULIFLOWER_CURD)));

    public static final Item PIZZA_SLICE = registerItem("pizza_slice",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.PIZZA_SLICE)));

    public static final Item CHEESE_SLICE = registerItem("cheese_slice",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.CHEESE_SLICE)));

    public static final Item CAULIFLOWER_SOUP = registerItem("cauliflower_soup",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.CAULIFLOWER_SOUP).maxCount(16)));

    public static final Item SPAGHETTI = registerItem("spaghetti",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.SPAGHETTI).maxCount(16)));

    public static final Item SALAD = registerItem("salad",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.SALAD).maxCount(16)));

    public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
            new AliasedBlockItem(FlavoredBlocks.CAULIFLOWER_STEM, new FabricItemSettings()));

    public static final Item CARBONARA = registerItem("carbonara",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.CARBONARA).maxCount(1)));


    public static final Item ANCIENT_SIGN = registerItem("ancient_sign",
            new SignItem(new FabricItemSettings().maxCount(16), FlavoredBlocks.STANDING_ANCIENT_SIGN, FlavoredBlocks.WALL_ANCIENT_SIGN));
    public static final Item HANGING_ANCIENT_SIGN = registerItem("ancient_hanging_sign",
            new HangingSignItem(FlavoredBlocks.HANGING_ANCIENT_SIGN, FlavoredBlocks.WALL_HANGING_ANCIENT_SIGN, new FabricItemSettings().maxCount(16)));

    public static final Item MASHED_POTATOES = registerItem("mashed_potatoes",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.MASHED_POTATOES).maxCount(16)));

    public static final Item GRILLED_VEGETABLES = registerItem("grilled_vegetables",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.GRILLED_VEGETABLES).maxCount(16)));

    public static final Item ALFREDO = registerItem("alfredo",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.ALFREDO).maxCount(16)));

    public static final Item FRUIT_SALAD = registerItem("fruit_salad",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.FRUIT_SALAD).maxCount(16)));

    public static final Item OCEAN_STEW = registerItem("ocean_stew",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.OCEAN_STEW).maxCount(16)));

    public static final Item PORRIDGE = registerItem("porridge",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.PORRIDGE).maxCount(16)));

    public static final Item SHAKSHOKA = registerItem("shakshoka",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.SHAKSHOKA).maxCount(16)));

    public static final Item MINESTRONE = registerItem("minestrone",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.MINESTRONE).maxCount(16)));



    public static final Item GLAZED_CHICKEN = registerItem("glazed_chicken",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.GLAZED_CHICKEN).maxCount(1)));

    public static final Item GLAZED_BEEF = registerItem("glazed_beef",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.GLAZED_BEEF).maxCount(1)));
    public static final Item GLAZED_MUTTON = registerItem("glazed_mutton",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.GLAZED_MUTTON).maxCount(1)));

    public static final Item GLAZED_PORK = registerItem("glazed_pork",
            new DishItem(new FabricItemSettings().food(FlavoredFoodComponents.GLAZED_PORK).maxCount(1)));
    public static final Item CANDIED_APPLE = registerItem("candied_apple",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.CANDIED_APPLE)));

    public static final Item ANCIENT_BOAT = TerraformBoatItemHelper.registerBoatItem(FlavoredBoats.ANCIENT_BOAT_ID, FlavoredBoats.ANCIENT_BOAT_KEY, false);
    public static final Item ANCIENT_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(FlavoredBoats.ANCIENT_CHEST_BOAT_ID, FlavoredBoats.ANCIENT_BOAT_KEY, true);


    public static final Item SWEET_BERRY_MUFFIN = registerItem("sweet_berry_muffin",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.SWEET_BERRY_MUFFIN)));

    public static final Item GLOW_BERRY_MUFFIN = registerItem("glow_berry_muffin",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.GLOW_BERRY_MUFFIN)));

    public static final Item GLOW_BERRY_COCKTAIL = registerItem("glow_berry_cocktail",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.GLOW_BERRY_COCKTAIL)));

    public static final Item SWEET_BERRY_COCKTAIL = registerItem("sweet_berry_cocktail",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.SWEET_BERRY_COCKTAIL)));

    public static final Item CHORUS_COCKTAIL = registerItem("chorus_cocktail",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.CHORUS_COCKTAIL)));


    public static final Item FIG_COCKTAIL = registerItem("fig_cocktail",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.FIG_COCKTAIL)));

    public static final Item HOT_COCOA = registerItem("hot_cocoa",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.HOT_COCOA)));

    public static final Item TEA = registerItem("tea",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.TEA)));


    public static final Item PUMPKIN_LATTE = registerItem("pumpkin_latte",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.PUMPKIN_LATTE)));

    public static final Item CIDER = registerItem("cider",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.CIDER)));

    public static final Item MALT = registerItem("malt",
            new DrinkItem(new FabricItemSettings().food(FlavoredFoodComponents.MALT)));

    public static final Item DELICACY_POTTERY_SHERD = registerItem("delicacy_pottery_sherd",
            new Item(new FabricItemSettings()));

    public static final Item REFRESH_POTTERY_SHERD = registerItem("refresh_pottery_sherd",
            new Item(new FabricItemSettings()));


    public static final Item SPINACH = registerItem("spinach",
            new Item(new FabricItemSettings().food(FlavoredFoodComponents.SPINACH)));

    public static final Item SPINACH_SEEDS = registerItem("spinach_seeds",
            new AliasedBlockItem(FlavoredBlocks.SPINACHES, new FabricItemSettings()));












    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Flavored.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Flavored.LOGGER.info("Registering Mod Items for " + Flavored.MOD_ID);


    }
}
