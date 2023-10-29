package com.mzuha.screen

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import net.minecraft.client.gui.screen.ingame.HandledScreens

object ModScreens {
    fun registerScreens() {
        HandledScreens.register(
            ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
            ::CrusherScreen
        )
        Machinix.logger.info(
            "Registering screens for $MOD_ID!"
        )
    }
}