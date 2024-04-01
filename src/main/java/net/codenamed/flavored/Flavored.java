package net.codenamed.flavored;

import net.codenamed.flavored.registry.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Very important comment
public class Flavored implements ModInitializer {

	public static final String MOD_ID = "flavored";
	public static final Logger LOGGER = LoggerFactory.getLogger("flavored");


	public static final Identifier CAULIFLOWER_FEATURE_ID = new Identifier("flavored", "cauliflower_feature");
	public static final VegetationPatchFeature  CAULIFLOWER_FEATURE = new VegetationPatchFeature(VegetationPatchFeatureConfig.CODEC);

	public static final RegistryKey<ConfiguredFeature<?, ?>> CAULIFLOWER_FEATURE_CONFIGURED = VegetationConfiguredFeatures.PATCH_PUMPKIN;

	public static RegistryKey<PlacedFeature> CAULIFLOWER_FEATURE_PLACED = VegetationPlacedFeatures.PATCH_PUMPKIN;

	private static final Identifier DATAPACK_ID = new Identifier("minecraft", "src/main/resources/resourcepacks/flavored_resources.zip");



	private static final Identifier JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/jungle_temple");

	private static final Identifier PLAINS_VILLAGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/village/village_plains_house");
	private static final Identifier TAIGA_VILLAGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/village/village_taiga_house");
	private static final Identifier DESERT_VILLAGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/village/village_desert_house");
	private static final Identifier SAVANNA_VILLAGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/village/village_savanna_house");

	private static final Identifier SNOWY_VILLAGE_CHEST_LOOT_TABLE_ID = new Identifier("minecraft", "chests/village/village_snowy_house");

	private static final Identifier SUSPICOUS_SAND_ID = new Identifier("minecraft", "archaeology/desert_pyramid");

	public  static  final  Identifier SNIFFER_DIGGABLE_ID = new Identifier("minecraft", "gameplay/sniffer_digging");

	@Override
	public void onInitialize() {

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
					if (source.isBuiltin() && JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID.equals(id)) {
						LootPool.Builder poolBuilder = LootPool.builder()
								.with(ItemEntry.builder(FlavoredItems.TOMATO_SEEDS));
						tableBuilder.pool(poolBuilder);
					}
			if (source.isBuiltin() && JUNGLE_TEMPLE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.GARLIC));
				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && PLAINS_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.GARLIC));
				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && DESERT_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.GARLIC));

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
			if (source.isBuiltin() && SAVANNA_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.GARLIC));
				tableBuilder.pool(poolBuilder);
			}
			if (source.isBuiltin() && SNOWY_VILLAGE_CHEST_LOOT_TABLE_ID.equals(id)) {
				LootPool.Builder poolBuilder = LootPool.builder()
						.with(ItemEntry.builder(FlavoredItems.ROSEMARY));
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









	}
}