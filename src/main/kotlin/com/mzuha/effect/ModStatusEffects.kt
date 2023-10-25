package com.mzuha.effect

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModStatusEffects {

    val RADIATION: StatusEffect = register("radiation", RadiationStatusEffect())

    private fun register(id: String, statusEffect: StatusEffect): StatusEffect {
        return Registry.register(Registries.STATUS_EFFECT, Identifier(MOD_ID, id), statusEffect)
    }

    fun registerStatusEffect() {
        Machinix.logger.info("Registering mod status effects for $MOD_ID!")
    }
}