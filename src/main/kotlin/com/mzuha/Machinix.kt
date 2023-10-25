package com.mzuha

import com.mzuha.block.MachinixBlocks
import com.mzuha.effect.ModStatusEffects
import com.mzuha.entity.ModBlockEntities
import com.mzuha.group.MachinixModGroup
import com.mzuha.item.MachinixItems
import com.mzuha.screen.ModScreenHandlers
import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

const val MOD_ID = "machinix"

object Machinix : ModInitializer {

    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        MachinixItems.registerModItems()
        MachinixBlocks.registerModBlocks()

        MachinixModGroup.registerModGroup()

        ModStatusEffects.registerStatusEffect()

        ModBlockEntities.registerModBlockEntities()

        ModScreenHandlers.registerModScreens()
    }
}