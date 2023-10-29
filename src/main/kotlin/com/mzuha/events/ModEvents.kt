package com.mzuha.events

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents

object ModEvents {
    fun registerModEvents() {
        ServerPlayerEvents.COPY_FROM.register(ModPlayerEventCopyFrom())
        Machinix.logger.info(
            "Registering mod events for $MOD_ID!"
        )
    }
}