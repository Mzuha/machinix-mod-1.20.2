package com.mzuha.datagen

import com.mzuha.block.MachinixBlocks
import com.mzuha.item.MachinixItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models

class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(MachinixBlocks.URANIUM_ORE)
        blockStateModelGenerator.registerSimpleState(MachinixBlocks.CRUSHER)
    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        itemModelGenerator.register(MachinixItems.URANIUM, Models.GENERATED)
    }
}