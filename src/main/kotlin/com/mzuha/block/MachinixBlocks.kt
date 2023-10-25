package com.mzuha.block

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import com.mzuha.block.custom.Crusher
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object MachinixBlocks {
    val CRUSHER: Block = registerBlock(
        "crusher",
        Crusher(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK))
    )

    val URANIUM_ORE: Block = registerBlock(
        "uranium_ore",
        Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE))
    )

    private fun registerBlock(name: String, block: Block): Block {
        registerBlockItem(name, block)
        return Registry.register(Registries.BLOCK, Identifier(MOD_ID, name), block)
    }

    private fun registerBlockItem(name: String, block: Block) {
        Registry.register(
            Registries.ITEM,
            Identifier(MOD_ID, name),
            BlockItem(block, FabricItemSettings())
        )
    }

    fun registerModBlocks() {
        Machinix.logger.info("Registering mod blocks for $MOD_ID!")
    }
}