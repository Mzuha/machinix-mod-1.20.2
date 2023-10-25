package com.mzuha.effect

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

class RadiationStatusEffect : StatusEffect(StatusEffectCategory.HARMFUL, 8889188) {
    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return true
    }

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        super.applyUpdateEffect(entity, amplifier)
        entity.damage(entity.damageSources.magic(), 2.0f)
    }
}