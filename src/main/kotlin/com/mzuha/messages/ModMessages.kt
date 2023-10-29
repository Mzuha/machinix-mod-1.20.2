package com.mzuha.messages

import com.mzuha.MOD_ID
import com.mzuha.messages.packet.CrusherEnrgySyncS2CPacket
import com.mzuha.messages.packet.ExampleC2SPacket
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.util.Identifier

object ModMessages {

    val EXAMPLE_ID = Identifier(MOD_ID, "example")
    val CRUSHER_ENERGY_SYNC_ID = Identifier(MOD_ID, "crusher_energy_sync")


    fun initializeC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive)
    }

    fun initializeS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(CRUSHER_ENERGY_SYNC_ID, CrusherEnrgySyncS2CPacket::receive)
    }
}