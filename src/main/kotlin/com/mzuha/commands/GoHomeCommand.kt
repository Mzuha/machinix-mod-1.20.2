package com.mzuha.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import com.mzuha.util.IModEntityDataSaver
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

object GoHomeCommand {
    fun register(
        dispatcher: CommandDispatcher<ServerCommandSource>,
        access: CommandRegistryAccess,
        env: CommandManager.RegistrationEnvironment
    ) {
        dispatcher.register(
            CommandManager.literal("home").executes(GoHomeCommand::run)
        )
    }

    private fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source.player as IModEntityDataSaver

        val home = player.persistentData.getIntArray("homepos")

        val serverPlayerEntity = context.source.player as ServerPlayerEntity

        if (home.isNotEmpty()) {
            serverPlayerEntity.requestTeleport(
                home[0].toDouble(), home[1].toDouble(), home[2].toDouble()
            )
            serverPlayerEntity.sendMessageToClient(Text.literal("Teleported to home!"), false)
            return 1
        } else {
            serverPlayerEntity.sendMessageToClient(Text.literal("No homes found!"), false)
            return -1
        }
    }
}