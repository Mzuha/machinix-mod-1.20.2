package com.mzuha.item.custom

import com.mzuha.effect.ModStatusEffects
import net.minecraft.entity.Entity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class Uranium(settings: Settings?) : Item(settings) {
    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (entity is PlayerEntity) {
            entity.setStatusEffect(
                StatusEffectInstance(
                    ModStatusEffects.RADIATION,
                    200,
                    0,
                ),
                null
            )
        }
        super.inventoryTick(stack, world, entity, slot, selected)
    }
}