package com.mzuha.keybinding

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import com.mzuha.messages.ModMessages
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil

object ModKeybinding {
    val SPAWN_COW_KEY = KeyBindingHelper.registerKeyBinding(
        KeyBinding(
            "machinix.key.spawn_cow",
            InputUtil.Type.KEYSYM,
            InputUtil.GLFW_KEY_R,
            "machinix.key.category.mod"
        )
    )

    fun registerKeybindings() {
        ClientTickEvents.END_CLIENT_TICK.register {
            while (SPAWN_COW_KEY.wasPressed()) {
                ClientPlayNetworking.send(
                    ModMessages.EXAMPLE_ID, PacketByteBufs.create()
                )
            }
        }
        Machinix.logger.info("Registering keybindings for $MOD_ID!")
    }
}