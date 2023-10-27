package com.mzuha.group

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import com.mzuha.block.ModBlocks
import com.mzuha.item.ModItems
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ModItemGroup {
    val MOD_GROUP: ItemGroup = Registry.register(
        Registries.ITEM_GROUP,
        Identifier(MOD_ID, "machinix"),
        FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.machinix"))
            .icon {
                ItemStack(ModItems.URANIUM)
            }
            .entries { _, entries ->
                entries.add(ModItems.URANIUM)
                entries.add(ModBlocks.CRUSHER)
                entries.add(ModBlocks.URANIUM_ORE)
            }
            .build()
    )

    fun registerModGroup() {
        Machinix.logger.info("Registering mod group for $MOD_ID!")
    }
}