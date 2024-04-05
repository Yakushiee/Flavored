package net.codenamed.flavored;

import net.codenamed.flavored.registry.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Very important comment
public class Flavored implements ModInitializer {

	public static final String MOD_ID = "flavored";
	public static final Logger LOGGER = LoggerFactory.getLogger("flavored");


	public static final RegistryKey<PlacedFeature> PATCH_CAULIFLOWER = registerKey("patch_cauliflower");
	public static final RegistryKey<PlacedFeature> PATCH_SPINACH = registerKey("patch_spinach");
	public static final RegistryKey<PlacedFeature> PATCH_ROSEMARY = registerKey("patch_rosemary");
	public static final RegistryKey<PlacedFeature> PATCH_GARLIC = registerKey("patch_garlic");


	public static RegistryKey<PlacedFeature> registerKey(String name) {
		return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Flavored.MOD_ID, name));
	}
	private static final Identifier DATAPACK_ID = new Identifier("minecraft", "src/main/resources/resourcepacks/flavored_resources.zip");



	private static final Identifier JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/jungle_temple");

	private static final Identifier DESERT_PYRAMID_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/desert_pyramid");


	private static final Identifier TAIGA_VILLAGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/village/village_taiga_house");
	private static final Identifier DESERT_VILLAGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/village/village_desert_house");
	private static final Identifier SAVANNA_VILLAGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/village/village_savanna_house");

	private static final Identifier SNOWY_VILLAGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/village/village_snowy_house");

	public  static  final  Identifier SNIFFER_DIGGABLE_ID = new Identifier("minecraft", "gameplay/sniffer_digging");





	@Override
	public void onInitialize() {

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
					if (source.isBuiltin() && JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID.equals(id)) {
						LootPool.Builder poolBuilder = LootPool.builder()
								.with(ItemEntry.builder(FlavoredItems.TOMATO_SEEDS));
						tableBuilder.pool(poolBuilder);
					}
			if (source.isBuiltin() && DESERT_PYRAMID_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.TOMATO_SEEDS));
				tableBuilder.pool(poolBuilder);
			}

			if (source.isBuiltin() && DESERT_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.TOMATO_SEEDS));

				tableBuilder.pool(poolBuilder);

			}
			if (source.isBuiltin() && SNOWY_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.GARLIC));
				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && TAIGA_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.GARLIC));
				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && SNOWY_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.SPINACH));
				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && SNOWY_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.SPINACH_SEEDS));
				tableBuilder.pool(poolBuilder);
			}

			if (source.isBuiltin() && TAIGA_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.ROSEMARY));
				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && SNIFFER_DIGGABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredBlocks.ANCIENT_SAPLING));
				tableBuilder.pool(poolBuilder);
			}



				});




		FlavoredItemGroup.registerItemGroups();
		FlavoredItems.registerModItems();
		FlavoredBlocks.registerModBlocks();
		FlavoredBlockEntities.registerBlockEntites();
		FlavoredBlocks.registerCrate();
		FlavoredRecipes.registerRecipes();
		FlavoredScreenHandlers.registerScreenHandlers();
		FlavoredStrippableBlocks.registerStrippables();
		FlavoredFlammableBlocks.registerFlammableBlocks();
		FlavoredVillagers.registerVillagers();
		FlavoredVillagerTrades.registerCustomTrades();
		FlavoredPaintings.registerPaintings();
		FlavoredBoats.registerBoats();

		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_CAULIFLOWER);

		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.TAIGA),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_GARLIC);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_PINE_TAIGA),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_GARLIC);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_GARLIC);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_GARLIC);

		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_ROSEMARY);

		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.TAIGA),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_ROSEMARY);


		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SNOWY_PLAINS),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_SPINACH);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SNOWY_TAIGA),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_SPINACH);
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SNOWY_SLOPES),
				GenerationStep.Feature.VEGETAL_DECORATION, PATCH_SPINACH);
	}










	}
