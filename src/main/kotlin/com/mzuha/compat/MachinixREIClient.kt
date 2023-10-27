package com.mzuha.compat

import com.mzuha.block.ModBlocks
import com.mzuha.recipe.CrusherRecipe
import com.mzuha.screen.CrusherScreen
import me.shedaniel.math.Rectangle
import me.shedaniel.rei.api.client.plugins.REIClientPlugin
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry
import me.shedaniel.rei.api.common.util.EntryStacks


class MachinixREIClient : REIClientPlugin {

    override fun registerCategories(registry: CategoryRegistry) {
        registry.add(CrusherCategory)
        registry.addWorkstations(CrusherCategory.CRUSHING, EntryStacks.of(ModBlocks.CRUSHER))
    }

    override fun registerDisplays(registry: DisplayRegistry) {
        registry.registerRecipeFiller(
            CrusherRecipe::class.java,
            CrusherRecipe.Type.INSTANCE,
            ::CrusherDisplay
        )
    }

    override fun registerScreens(registry: ScreenRegistry) {
        registry.registerClickArea(
            { Rectangle(75, 30, 20, 30) },
            CrusherScreen::class.java,
            CrusherCategory.CRUSHING
        )
    }
}