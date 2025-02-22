package net.codenamed.flavored;

import net.codenamed.flavored.datagen.FlavoredWorldGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.codenamed.flavored.registry.FlavoredConfiguredFeatures;

public class FlavoredDataGenerator implements DataGeneratorEntrypoint {

    
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {


    }
}
