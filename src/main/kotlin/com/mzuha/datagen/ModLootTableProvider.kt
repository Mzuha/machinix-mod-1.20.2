package com.mzuha.datagen

import com.mzuha.block.MachinixBlocks
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider


class ModLootTableProvider(dataOutput: FabricDataOutput) : FabricBlockLootTableProvider(dataOutput) {
    override fun generate() {
        addDrop(MachinixBlocks.URANIUM_ORE)
        addDrop(MachinixBlocks.CRUSHER)
    }
}