package com.mzuha.screen

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.network.PacketByteBuf
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.Identifier

object ModScreenHandlers {
    val CRUSHER_SCREEN_HANDLER: ScreenHandlerType<CrusherScreenHandler> = Registry.register(
        Registries.SCREEN_HANDLER,
        Identifier(MOD_ID, "crushing"),
        ExtendedScreenHandlerType { syncId: Int, playerInventory: PlayerInventory, buf: PacketByteBuf ->
            CrusherScreenHandler(syncId, playerInventory, buf)
        }
    )

    fun registerModScreens() {
        Machinix.logger.info("Registering mod scrrens for $MOD_ID!")
    }
}