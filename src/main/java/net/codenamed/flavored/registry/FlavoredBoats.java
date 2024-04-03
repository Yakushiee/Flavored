package net.codenamed.flavored.registry;


import net.codenamed.flavored.Flavored;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class FlavoredBoats {

        public static final Identifier ANCIENT_BOAT_ID = new Identifier(Flavored.MOD_ID, "ancient_boat");
        public static final Identifier ANCIENT_CHEST_BOAT_ID = new Identifier(Flavored.MOD_ID, "ancient_chest_boat");

        public static final RegistryKey<TerraformBoatType> ANCIENT_BOAT_KEY = TerraformBoatTypeRegistry.createKey(Identifier.of(Flavored.MOD_ID, "ancient"));


    public static void registerBoats() {
            TerraformBoatType ancientBoat = new TerraformBoatType.Builder()
                    .item(FlavoredItems.ANCIENT_BOAT)
                    .chestItem(FlavoredItems.ANCIENT_CHEST_BOAT)
                    .planks(FlavoredBlocks.ANCIENT_PLANKS.asItem())
                    .build();

            Registry.register(TerraformBoatTypeRegistry.INSTANCE, ANCIENT_BOAT_KEY, ancientBoat);
        }
    }