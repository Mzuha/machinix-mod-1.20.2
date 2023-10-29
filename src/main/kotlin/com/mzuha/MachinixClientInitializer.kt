package com.mzuha

import com.mzuha.commands.ModCommands
import com.mzuha.events.ModEvents
import com.mzuha.messages.ModMessages
import com.mzuha.screen.ModScreens
import net.fabricmc.api.ClientModInitializer

class MachinixClientInitializer : ClientModInitializer {
    override fun onInitializeClient() {
        ModScreens.registerScreens()

        ModCommands.registerModCommands()

        ModEvents.registerModEvents()

        ModMessages.initializeS2CPackets()
    }
}