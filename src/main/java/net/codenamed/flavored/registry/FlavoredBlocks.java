package net.codenamed.flavored.registry;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.codenamed.flavored.helper.WoodRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.codenamed.flavored.Flavored;
import net.codenamed.flavored.block.custom.*;
import net.codenamed.flavored.world.tree.AncientSaplingGenerator;

public class FlavoredBlocks {

    public static final Block ANCIENT_LOG = registerBlock("ancient_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));

    public static final Block STRIPPED_ANCIENT_LOG = registerBlock("stripped_ancient_log",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));

    public static final Block ANCIENT_WOOD = registerBlock("ancient_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.OAK_WOOD)));
    public static final Block STRIPPED_ANCIENT_WOOD = registerBlock("stripped_ancient_wood",
            new PillarBlock(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));

    public static final Block ANCIENT_PLANKS = registerBlock("ancient_planks",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));

    public static final Block ANCIENT_STAIRS = registerBlock("ancient_stairs",
            new StairsBlock(FlavoredBlocks.ANCIENT_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(Blocks.OAK_STAIRS)));

    public static final Block ANCIENT_SLAB = registerBlock("ancient_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.OAK_SLAB)));

    public static final Block ANCIENT_DOOR = registerBlock("ancient_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_DOOR), BlockSetType.OAK));

    public static final Block ANCIENT_TRAPDOOR = registerBlock("ancient_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.OAK_TRAPDOOR), BlockSetType.OAK));

    public static final Block ANCIENT_FENCE = registerBlock("ancient_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE)));

    public static final Block ANCIENT_FENCE_GATE = registerBlock("ancient_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.OAK_FENCE_GATE), WoodType.OAK));

    public static final Block ANCIENT_BUTTON = registerBlock("ancient_button",
            Blocks.createWoodenButtonBlock(BlockSetType.OAK));

    public static final Block ANCIENT_PRESSURE_PLATE = registerBlock("ancient_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.OAK_PRESSURE_PLATE), BlockSetType.OAK));

    static final Identifier ANCIENT_SIGN_TEXTURE = Identifier.of(Flavored.MOD_ID, "entity/signs/ancient");
    public static  TerraformSignBlock  ANCIENT_SIGN = WoodRegistry.register("ancient_sign", new TerraformSignBlock(ANCIENT_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)));
    public static  TerraformWallSignBlock ANCIENT_WALL_SIGN = WoodRegistry.register("ancient_wall_sign", new TerraformWallSignBlock(ANCIENT_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN).dropsLike(ANCIENT_SIGN)));
    static final Identifier ANCIENT_HANGING_SIGN_TEXTURE = Identifier.of(Flavored.MOD_ID, "entity/signs/hanging/ancient");
    static final Identifier ANCIENT_HANGING_SIGN_GUI_TEXTURE = Identifier.of(Flavored.MOD_ID, "textures/gui/hanging_signs/ancient");
    public static TerraformHangingSignBlock ANCIENT_HANGING_SIGN = WoodRegistry.register("ancient_hanging_sign", new TerraformHangingSignBlock(ANCIENT_HANGING_SIGN_TEXTURE, ANCIENT_HANGING_SIGN_GUI_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)));
    public static TerraformWallHangingSignBlock ANCIENT_WALL_HANGING_SIGN = WoodRegistry.register("ancient_wall_hanging_sign", new TerraformWallHangingSignBlock(ANCIENT_HANGING_SIGN_TEXTURE, ANCIENT_HANGING_SIGN_GUI_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN).dropsLike(ANCIENT_HANGING_SIGN)));

    public static final Block ANCIENT_LEAVES = registerBlock("ancient_leaves",
            new LeavesBlock(FabricBlockSettings.copyOf(Blocks.AZALEA_LEAVES)));

    public static final Block ANCIENT_SAPLING = registerBlock("ancient_sapling",
            new SaplingBlock(new AncientSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));

    public static final Block FLOWERING_ANCIENT_LEAVES = registerBlock("flowering_ancient_leaves",
            new FloweringAncientLeavesBlock(FabricBlockSettings.copyOf(Blocks.AZALEA_LEAVES).ticksRandomly()));


    public static final Block PIZZA = registerPlaceableItem("pizza",
            new PizzaBlock(FabricBlockSettings.copyOf(Blocks.CAKE).strength(0.5f, 0.5f)));

    public static final Block GARLIC_BREAD = registerPlaceableItem("garlic_bread",
            new GarlicBreadBlock(FabricBlockSettings.copyOf(Blocks.CAKE).strength(0.5f, 0.5f)));

    public static final Block PUDDING = registerPlaceableItem("pudding",
            new PuddingBlock(FabricBlockSettings.copyOf(Blocks.CAKE).strength(0.4f, 0.4f)));

    public static final Block CHEESE = registerPlaceableItem("cheese",
            new CheeseBlock(FabricBlockSettings.copyOf(Blocks.CAKE).strength(0.5f, 0.5f)));

    public static final Block PLENTY = registerBlock("plenty",
            new PlentyBlock(FabricBlockSettings.copyOf(Blocks.CAKE).strength(0.5f, 0.5f)));

    public static final Block CAULIFLOWER = registerBlock("cauliflower",
            new CauliflowerBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).instrument(Instrument.DIDGERIDOO).strength(1.0F).sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block PLANT_POT = registerBlock("plant_pot",
            new PlantPotBlock(FabricBlockSettings.copyOf(Blocks.DECORATED_POT).strength(1.5F, 3.0F).sounds(BlockSoundGroup.STONE).nonOpaque().requiresTool()));
    public static final Block FERMENTER = registerBlock("fermenter",
            new FermenterBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK).ticksRandomly()));

    public static final Block OVEN = registerBlock("oven",
            new OvenBlock(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS).luminance(Blocks.createLightLevelFromLitBlockState(13)).ticksRandomly()));

    public static final Block RANGE = registerBlock("range",
            new RangeBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON).luminance(Blocks.createLightLevelFromLitBlockState(13))));

    public static final Block BOILER = registerBlock("boiler",
            new BoilerBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON)));

    public static final Block WILD_SPINACH = registerBlock("wild_spinach",
            new Block(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH)));

    public static final Block WILD_GARLIC = registerBlock("wild_garlic",
            new Block(FabricBlockSettings.copyOf(Blocks.SWEET_BERRY_BUSH)));





    public static final Block CAULIFLOWER_STEM = registerBlock("cauliflower_stem",
            new StemBlock((GourdBlock)CAULIFLOWER, () -> {
                return FlavoredItems.CAULIFLOWER_SEEDS;
            }, FabricBlockSettings.create().mapColor(MapColor.LICHEN_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.STEM).pistonBehavior(PistonBehavior.DESTROY)));


    public static final Block ATTACHED_CAULIFLOWER_STEM = registerBlock("attached_cauliflower_stem",
            new AttachedStemBlock((GourdBlock)CAULIFLOWER, () -> {
                return FlavoredItems.CAULIFLOWER_SEEDS;
            }, FabricBlockSettings.create().mapColor(MapColor.LICHEN_GREEN).noCollision().breakInstantly().sounds(BlockSoundGroup.WOOD).pistonBehavior(PistonBehavior.DESTROY)));



    public static final Block CARVED_CAULIFLOWER = registerBlock("carved_cauliflower",
            new WearableCarvedCauliflowerBlock(FabricBlockSettings.create().mapColor(MapColor.WHITE).strength(1.0F).sounds(BlockSoundGroup.WOOD).allowsSpawning(Blocks::always).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block CARVED_MELON = registerBlock("carved_melon",
            new WearableCarvedMelonBlock(FabricBlockSettings.create().mapColor(MapColor.GREEN).strength(1.0F).sounds(BlockSoundGroup.WOOD).allowsSpawning(Blocks::always).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block GARLICS = registerBlock("garlics",
            new GarlicsBlock(FabricBlockSettings.copyOf(Blocks.CARROTS)
                    .mapColor(MapColor.PALE_GREEN)
                    .noCollision().ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block SPINACHES = registerBlock("spinaches",
            new SpinachesBlock(FabricBlockSettings.copyOf(Blocks.CARROTS)
                    .mapColor(MapColor.GREEN)
                    .noCollision().ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block TOMATO_CROP = registerBlockWithoutItem("tomato_crop",
            new TomatoCropBlock(FabricBlockSettings.copy(Blocks.SWEET_BERRY_BUSH)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));

    public static final Block ROSEMARY_BUSH = registerBlockWithoutItem("rosemary_bush",
            new RosemaryBushBlock(FabricBlockSettings.copy(Blocks.SWEET_BERRY_BUSH)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));

    public static final Block FIG = registerBlockWithoutItem("fig",
            new FigBlock(FabricBlockSettings.copy(Blocks.SWEET_BERRY_BUSH)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .nonOpaque()
                    .sounds(BlockSoundGroup.SWEET_BERRY_BUSH)));




    public static Block CRATE = null;



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Flavored.MOD_ID, name), block);
    }

    private static Block registerPlaceableItem(String name, Block block) {
        registerPlaceableBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Flavored.MOD_ID, name), block);
    }

    public  static  void  registerCrate() {


        CRATE = registerBlock("crate",
                new CrateBlock(true, Items.AIR, FabricBlockSettings.copyOf(Blocks.COMPOSTER)));





    }

    private static Item registerPlaceableBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Flavored.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().maxCount(1)));
        return item;
    }

    private static Item registerBlockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(Flavored.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }

    private static Block registerBlockWithoutItem(String name, Block block) {

        return Registry.register(Registries.BLOCK, new Identifier(Flavored.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
        Flavored.LOGGER.info("Registering ModBlocks for " + Flavored.MOD_ID);
    }
}
