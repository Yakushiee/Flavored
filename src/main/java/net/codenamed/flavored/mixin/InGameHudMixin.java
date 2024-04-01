package net.codenamed.flavored.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import net.codenamed.flavored.registry.FlavoredBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @Shadow @Final private MinecraftClient client;

    private int scaledWidth;
    private int scaledHeight;

    public InGameHudMixin() {
        super();
    }

    @Unique
    private static final Identifier MELON_BLUR = new Identifier("flavored","textures/misc/melonblur.png");
    @Unique
    private static final Identifier CAULIFLOWER_BLUR = new Identifier("flavored", "textures/misc/cauliflowerblur.png");


    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void renderCarved(DrawContext context, float tickDelta, CallbackInfo ci) {
        this.scaledWidth = context.getScaledWindowWidth();
        this.scaledHeight = context.getScaledWindowHeight();
        RenderSystem.enableBlend();
        ItemStack itemStack = this.client.player.getInventory().getArmorStack(3);
        if (itemStack.isOf(FlavoredBlocks.CARVED_MELON.asItem())) {
            this.renderOverlay(context, MELON_BLUR, 1.0F);
        }
        else if (itemStack.isOf(FlavoredBlocks.CARVED_CAULIFLOWER.asItem())) {
            this.renderOverlay(context, CAULIFLOWER_BLUR, 1.0F);
        }
    }

    @Inject(method = "renderOverlay", at = @At("HEAD"), cancellable = true)
    private void renderCarvedOverlay(DrawContext context, Identifier texture, float opacity, CallbackInfo ci) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        context.setShaderColor(1.0F, 1.0F, 1.0F, opacity);
        context.drawTexture(texture, 0, 0, -90, 0.0F, 0.0F, this.scaledWidth, this.scaledHeight, this.scaledWidth, this.scaledHeight);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}