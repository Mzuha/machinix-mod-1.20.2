package com.mzuha.compat

import com.mzuha.MOD_ID
import com.mzuha.block.ModBlocks
import java.util.LinkedList
import me.shedaniel.math.Point
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.gui.Renderer
import me.shedaniel.rei.api.client.gui.widgets.Widget
import me.shedaniel.rei.api.client.gui.widgets.Widgets
import me.shedaniel.rei.api.client.registry.display.DisplayCategory
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.basic.BasicDisplay
import me.shedaniel.rei.api.common.util.EntryStacks
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object CrusherCategory : DisplayCategory<BasicDisplay> {

    val TEXTURE = Identifier(MOD_ID, "textures/gui/crusher_gui.png")
    val CRUSHING = CategoryIdentifier.of<CrusherDisplay>(MOD_ID, "crushing")

    override fun getCategoryIdentifier(): CategoryIdentifier<out BasicDisplay> = CRUSHING

    override fun getTitle(): Text = Text.translatable("rei.title.crusher")

    override fun getIcon(): Renderer = EntryStacks.of(ModBlocks.CRUSHER.asItem().defaultStack)

    override fun setupDisplay(display: BasicDisplay, bounds: Rectangle): MutableList<Widget> {
        val startPoint = Point(bounds.centerX - 87, bounds.centerY - 35)

        val widgets: LinkedList<Widget> = LinkedList()
        widgets.add(
            Widgets.createTexturedWidget(
                TEXTURE, Rectangle(startPoint.x, startPoint.y, 175, 82)
            )
        )

        widgets.add(
            Widgets.createSlot(
                Point(startPoint.x + 80, startPoint.y + 11)
            ).entries(display.inputEntries[0])
        )
        widgets.add(
            Widgets.createSlot(
                Point(startPoint.x + 80, startPoint.y + 59)
            ).markOutput()
                .entries(display.outputEntries[0])
        )

        return widgets
    }

    override fun getDisplayHeight(): Int = 90
}