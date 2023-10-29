package com.mzuha.screen

import com.mojang.blaze3d.systems.RenderSystem
import com.mzuha.MOD_ID
import com.mzuha.screen.renderer.EnergyInfoArea
import com.mzuha.util.MouseUtil.isMouseOver
import java.util.Optional
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.ingame.HandledScreen
import net.minecraft.client.render.GameRenderer
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text
import net.minecraft.util.Identifier

class CrusherScreen(
    handler: CrusherScreenHandler,
    inventory: PlayerInventory,
    title: Text
) : HandledScreen<CrusherScreenHandler>(handler, inventory, title) {

    private val TEXTURE: Identifier = Identifier(MOD_ID, "textures/gui/crusher_gui.png")

    private lateinit var energyInfoArea: EnergyInfoArea

    override fun init() {
        super.init()
        assignEnergyInfoArea()
    }

    private fun assignEnergyInfoArea() {
        energyInfoArea = EnergyInfoArea(
            (width - backgroundWidth) / 2 + 156,
            (height - backgroundHeight) / 2 + 13,
            handler.crusherEntity.energyStorage
        )
    }

    override fun drawForeground(context: DrawContext, mouseX: Int, mouseY: Int) {
        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2

        renderEnergyAreaTooltips(context, mouseX, mouseY, x, y)
    }

    private fun renderEnergyAreaTooltips(context: DrawContext, pMouseX: Int, pMouseY: Int, x: Int, y: Int) {
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 156, 13, 8, 64)) {
            val energyStorage = handler.crusherEntity.energyStorage
            context.drawTooltip(
                client?.textRenderer,
                listOf(Text.literal(energyStorage.amount.toString() + "/" + energyStorage.capacity + " E")),
                Optional.empty(),
                pMouseX - x, pMouseY - y
            )
        }
    }

    override fun drawBackground(context: DrawContext, delta: Float, mouseX: Int, mouseY: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram)
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)
        RenderSystem.setShaderTexture(0, TEXTURE)

        val x = (width - backgroundWidth) / 2
        val y = (height - backgroundHeight) / 2

        context.drawTexture(TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight)
        renderProgressArrow(context, x, y)
        energyInfoArea.draw(context)
    }

    private fun renderProgressArrow(context: DrawContext, x: Int, y: Int) {
        if (handler.isCrafting()) {
            context.drawTexture(TEXTURE, x + 85, y + 30, 176, 0, 8, handler.getScaledProgress())
        }
    }

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        renderBackground(context, mouseX, mouseY, delta)
        super.render(context, mouseX, mouseY, delta)
        drawMouseoverTooltip(context, mouseX, mouseY)
    }

    private fun isMouseAboveArea(
        pMouseX: Int,
        pMouseY: Int,
        x: Int,
        y: Int,
        offsetX: Int,
        offsetY: Int,
        width: Int,
        height: Int
    ): Boolean {
        return isMouseOver(pMouseX.toDouble(), pMouseY.toDouble(), x + offsetX, y + offsetY, width, height)
    }
}