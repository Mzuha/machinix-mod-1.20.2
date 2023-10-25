package com.mzuha.item

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import com.mzuha.item.custom.Uranium
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object MachinixItems {

    val URANIUM: Item = registerItem(
        "uranium",
        Uranium(FabricItemSettings())
    )

    private fun registerItem(name: String, item: Item): Item {
        return Registry.register(Registries.ITEM, Identifier(MOD_ID, name), item)
    }

    fun registerModItems() {
        Machinix.logger.info("Registering mod items for $MOD_ID!")
    }
}