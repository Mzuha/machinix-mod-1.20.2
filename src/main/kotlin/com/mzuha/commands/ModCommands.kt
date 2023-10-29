package com.mzuha.commands

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback

object ModCommands {
    fun registerModCommands() {
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register)
        CommandRegistrationCallback.EVENT.register(GoHomeCommand::register)
        Machinix.logger.info(
            "Registering mod commands for $MOD_ID!"
        )
    }
}