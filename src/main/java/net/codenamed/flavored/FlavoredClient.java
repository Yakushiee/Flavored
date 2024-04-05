package net.codenamed.flavored;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.codenamed.flavored.registry.FlavoredBoats;
import net.codenamed.flavored.registry.FlavoredScreenHandlers;
import net.codenamed.flavored.screen.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.codenamed.flavored.registry.FlavoredBlocks;
import net.codenamed.flavored.registry.FlavoredBlockEntities;
import net.codenamed.flavored.block.entity.renderer.RangeBlockEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

public class FlavoredClient implements ClientModInitializer {
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.ROSEMARY_BUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.GARLICS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.CAULIFLOWER_STEM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.ATTACHED_CAULIFLOWER_STEM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.SPINACHES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.ANCIENT_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.ANCIENT_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.FIG, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.ANCIENT_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.ANCIENT_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.FLOWERING_ANCIENT_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.WILD_SPINACH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.WILD_GARLIC, RenderLayer.getCutout());




        HandledScreens.register(FlavoredScreenHandlers.BOILER_SCREEN_HANDLER, BoilerScreen::new);

        HandledScreens.register(FlavoredScreenHandlers.FERMENTER_SCREEN_HANDLER, FermenterScreen::new);
        HandledScreens.register(FlavoredScreenHandlers.OVEN_SCREEN_HANDLER, OvenScreen::new);
        HandledScreens.register(FlavoredScreenHandlers.RANGE_SCREEN_HANDLER, RangeScreen::new);


        BlockEntityRendererFactories.register(FlavoredBlockEntities.RANGE_BLOCK_ENTITY, RangeBlockEntityRenderer::new);

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, FlavoredBlocks.ANCIENT_SIGN.getTexture()));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, FlavoredBlocks.ANCIENT_HANGING_SIGN.getTexture()));

        TerraformBoatClientHelper.registerModelLayers(new Identifier(Flavored.MOD_ID, "ancient"), false);


    }
    }
