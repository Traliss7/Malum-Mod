package com.sammy.malum.client.screen.container;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sammy.malum.common.container.SpiritPouchContainer;
import com.sammy.malum.core.helper.DataHelper;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;
import java.awt.*;

public class SpiritPouchContainerScreen extends AbstractContainerScreen<SpiritPouchContainer> {
    public static final ResourceLocation BACKGROUND = DataHelper.prefix("textures/gui/spirit_pouch.png");
    public static final Color textColor = new Color(49, 35, 41);

    public SpiritPouchContainerScreen(SpiritPouchContainer screenContainer, Inventory inv, Component titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(@Nonnull PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }

    @Override
    protected void renderTooltip(@Nonnull PoseStack poseStack, int mouseX, int mouseY) {
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
            this.renderTooltip(poseStack, this.hoveredSlot.getItem(), mouseX, mouseY);
        }
    }

    @Override
    protected void renderLabels(@Nonnull PoseStack poseStack, int mouseX, int mouseY) {
        if (this.minecraft != null && this.minecraft.player != null) {
//            Color color = textColor;
//            Color insideColor = MalumHelper.darker(color, 3);
//            Color outlineColor = MalumHelper.darker(color, 2);
//            String text = title.getString();
//            float x = 89 - font.getStringWidth(text) / 2f;
//            float y = 6;

//            font.drawString(poseStack, text, x, y - 1, packColor(96, outlineColor.getRed(), outlineColor.getGreen(), outlineColor.getBlue()));
//            font.drawString(poseStack, text, x - 1, y, packColor(96, outlineColor.getRed(), outlineColor.getGreen(), outlineColor.getBlue()));
//            font.drawString(poseStack, text, x + 1, y, packColor(96, outlineColor.getRed(), outlineColor.getGreen(), outlineColor.getBlue()));
//            font.drawString(poseStack, text, x, y + 1, packColor(96, outlineColor.getRed(), outlineColor.getGreen(), outlineColor.getBlue()));
//
//            font.drawString(poseStack, text, x, y, packColor(255, insideColor.getRed(), insideColor.getGreen(), insideColor.getBlue()));
        }
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int x, int y) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
}