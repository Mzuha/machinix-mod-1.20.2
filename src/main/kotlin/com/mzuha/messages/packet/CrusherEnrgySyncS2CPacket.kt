package com.mzuha.messages.packet

import com.mzuha.entity.CrusherEntity
import com.mzuha.screen.CrusherScreenHandler
import net.fabricmc.fabric.api.networking.v1.PacketSender
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.network.PacketByteBuf

object CrusherEnrgySyncS2CPacket {
    fun receive(
        client: MinecraftClient,
        handler: ClientPlayNetworkHandler,
        buf: PacketByteBuf,
        responseSender: PacketSender
    ) {
        val energyLevel = buf.readLong()
        val blockPos = buf.readBlockPos()

        val entity = client.world?.getBlockEntity(blockPos)

        if (entity is CrusherEntity) {

            entity.setEnergyLevel(energyLevel)

            val screenHandler = client.player?.currentScreenHandler
            if (screenHandler is CrusherScreenHandler
                && screenHandler.crusherEntity.pos.equals(blockPos)) {
                entity.setEnergyLevel(energyLevel)
            }
        }
    }
}