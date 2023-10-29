package com.mzuha.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import com.mzuha.util.IModEntityDataSaver
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.server.command.CommandManager
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text

object SetHomeCommand {
    fun register(
        dispatcher: CommandDispatcher<ServerCommandSource>,
        access: CommandRegistryAccess,
        env: CommandManager.RegistrationEnvironment
    ) {
        dispatcher.register(
            CommandManager.literal("sethome").executes(SetHomeCommand::run)
        )
    }

    private fun run(context: CommandContext<ServerCommandSource>): Int {
        val player = context.source.player as IModEntityDataSaver

        val serverPlayerEntity = context.source.player as ServerPlayerEntity
        val blockPos = serverPlayerEntity.blockPos
        player.persistentData.putIntArray(
            "homepos",
            intArrayOf(
                blockPos.x,
                blockPos.y,
                blockPos.z
            )
        )
        serverPlayerEntity.sendMessageToClient(Text.literal("Home set succesfully!"), false)
        return 1
    }
}