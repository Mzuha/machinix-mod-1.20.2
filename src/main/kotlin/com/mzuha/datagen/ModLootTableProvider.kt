package com.mzuha.datagen

import com.mzuha.block.ModBlocks
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider


class ModLootTableProvider(dataOutput: FabricDataOutput) : FabricBlockLootTableProvider(dataOutput) {
    override fun generate() {
        addDrop(ModBlocks.URANIUM_ORE)
        addDrop(ModBlocks.CRUSHER)
    }
}