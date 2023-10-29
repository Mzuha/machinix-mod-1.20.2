package com.mzuha.events

import com.mzuha.util.IModEntityDataSaver
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.minecraft.server.network.ServerPlayerEntity

class ModPlayerEventCopyFrom : ServerPlayerEvents.CopyFrom {
    override fun copyFromPlayer(oldPlayer: ServerPlayerEntity?, newPlayer: ServerPlayerEntity?, alive: Boolean) {
        val original = oldPlayer as IModEntityDataSaver
        val player = newPlayer as IModEntityDataSaver

        player.persistentData.putIntArray("homepos", original.persistentData.getIntArray("homepos"))
    }
}