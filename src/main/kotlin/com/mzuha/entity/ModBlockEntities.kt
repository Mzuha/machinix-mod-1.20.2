package com.mzuha.entity

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import com.mzuha.block.ModBlocks
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModBlockEntities {
    val CRUSHER_ENTITY: BlockEntityType<CrusherEntity> = Registry.register(
        Registries.BLOCK_ENTITY_TYPE,
        Identifier(MOD_ID, "crusher_entity"),
        FabricBlockEntityTypeBuilder.create(
            ::CrusherEntity,
            ModBlocks.CRUSHER
        ).build()
    )

    fun registerModBlockEntities() {
        Machinix.logger.info("Registering mod block entities for $MOD_ID!")
    }
}