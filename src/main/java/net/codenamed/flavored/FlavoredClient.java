package net.codenamed.flavored;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.codenamed.flavored.block.FlavoredBlocks;
import net.codenamed.flavored.block.entity.FlavoredBlockEntities;
import net.codenamed.flavored.block.entity.renderer.RangeBlockEntityRenderer;
import net.codenamed.flavored.screen.FermenterScreen;
import net.codenamed.flavored.screen.FlavoredScreenHandlers;
import net.codenamed.flavored.screen.OvenScreen;
import net.codenamed.flavored.screen.RangeScreen;

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
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.PALM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.DATES, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.PALM_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(FlavoredBlocks.PALM_DOOR, RenderLayer.getCutout());





        HandledScreens.register(FlavoredScreenHandlers.FERMENTER_SCREEN_HANDLER, FermenterScreen::new);
        HandledScreens.register(FlavoredScreenHandlers.OVEN_SCREEN_HANDLER, OvenScreen::new);
        HandledScreens.register(FlavoredScreenHandlers.RANGE_SCREEN_HANDLER, RangeScreen::new);

        BlockEntityRendererFactories.register(FlavoredBlockEntities.RANGE_BLOCK_ENTITY, RangeBlockEntityRenderer::new);

    }
    }