package com.mzuha

import com.mzuha.block.ModBlocks
import com.mzuha.effect.ModStatusEffects
import com.mzuha.entity.ModBlockEntities
import com.mzuha.group.ModItemGroup
import com.mzuha.item.ModItems
import com.mzuha.recipe.ModRecipes
import com.mzuha.screen.ModScreenHandlers
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val MOD_ID = "machinix"

object Machinix : ModInitializer {

    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        ModItems.registerModItems()
        ModBlocks.registerModBlocks()

        ModItemGroup.registerModGroup()

        ModStatusEffects.registerStatusEffect()

        ModBlockEntities.registerModBlockEntities()

        ModRecipes.registerModRecipes()

        ModScreenHandlers.registerModScreens()
    }
}