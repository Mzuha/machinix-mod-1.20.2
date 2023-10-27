package com.mzuha.recipe

import com.mzuha.MOD_ID
import com.mzuha.Machinix
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModRecipes {
    fun registerModRecipes() {
        Registry.register(
            Registries.RECIPE_SERIALIZER,
            Identifier(MOD_ID, CrusherRecipe.Serializer.ID),
            CrusherRecipe.Serializer.INSTANCE
        )

        Registry.register(
            Registries.RECIPE_TYPE,
            Identifier(MOD_ID, CrusherRecipe.Type.ID),
            CrusherRecipe.Type.INSTANCE
        )

        Machinix.logger.info("Registering mod reciped for $MOD_ID!")
    }
}