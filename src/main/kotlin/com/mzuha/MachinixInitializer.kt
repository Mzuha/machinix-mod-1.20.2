package com.mzuha

import com.mzuha.screen.CrusherScreen
import com.mzuha.screen.ModScreenHandlers
import net.fabricmc.api.ClientModInitializer
import net.minecraft.client.gui.screen.ingame.HandledScreens

class MachinixInitializer : ClientModInitializer {
    override fun onInitializeClient() {
        HandledScreens.register(
            ModScreenHandlers.CRUSHER_SCREEN_HANDLER,
            ::CrusherScreen
        )
    }
}