package com.mzuha.messages.packet

import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnReason
import net.minecraft.network.PacketByteBuf
import net.minecraft.server.MinecraftServer
import net.minecraft.server.network.ServerPlayNetworkHandler
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld

object ExampleC2SPacket {
    fun receive(
        server: MinecraftServer,
        player: ServerPlayerEntity,
        handler: ServerPlayNetworkHandler,
        buf: PacketByteBuf,
        responseSender: PacketSender
    ) {
        EntityType.COW.spawn(
            player.world as ServerWorld,
            null,
            null,
            player.blockPos,
            SpawnReason.TRIGGERED,
            true,
            false
        )
    }
}