package com.mzuha.recipe

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.mzuha.entity.CRUSHER_INPUT_SLOT
import com.mzuha.entity.CRUSHER_OUTPUT_SLOT
import net.minecraft.inventory.SimpleInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeCodecs
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.dynamic.Codecs
import net.minecraft.world.World


class CrusherRecipe(
    private val recipeIngredients: List<Ingredient>,
    private val output: ItemStack
) : Recipe<SimpleInventory> {
    override fun matches(inventory: SimpleInventory, world: World): Boolean {
        if (world.isClient) return false
        return recipeIngredients[CRUSHER_INPUT_SLOT].test(inventory.getStack(CRUSHER_INPUT_SLOT))
            && (output.item == inventory.getStack(CRUSHER_OUTPUT_SLOT).item || inventory.getStack(CRUSHER_OUTPUT_SLOT).isEmpty)
    }

    override fun craft(inventory: SimpleInventory, registryManager: DynamicRegistryManager): ItemStack =
        output

    override fun fits(width: Int, height: Int): Boolean = true

    override fun getResult(registryManager: DynamicRegistryManager?): ItemStack = output

    override fun getIngredients(): DefaultedList<Ingredient> =
        DefaultedList.ofSize<Ingredient?>(recipeIngredients.size).apply {
            addAll(recipeIngredients)
        }

    override fun getType(): RecipeType<*> = Type.INSTANCE

    override fun getSerializer(): RecipeSerializer<*> = Serializer.INSTANCE

    object Type : RecipeType<CrusherRecipe> {
        val INSTANCE = Type
        const val ID = "crushing"
    }

    object Serializer : RecipeSerializer<CrusherRecipe> {

        val INSTANCE = Serializer
        const val ID = "crushing"

        private val CODEC: Codec<CrusherRecipe> = RecordCodecBuilder.create { instance ->
            instance.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 9).fieldOf("ingredients").forGetter { it.ingredients },
                RecipeCodecs.CRAFTING_RESULT.fieldOf("output").forGetter { it.output }
            ).apply(instance, ::CrusherRecipe)
        }

        private fun validateAmount(delegate: Codec<Ingredient>, max: Int): Codec<List<Ingredient>> {
            return Codecs.validate(Codecs.validate(
                delegate.listOf()
            ) { list: List<Ingredient> ->
                if (list.size > max) DataResult.error { "Recipe has too many ingredients!" } else DataResult.success(
                    list
                )
            }
            ) { list: List<Ingredient> ->
                if (list.isEmpty()) DataResult.error { "Recipe has no ingredients!" } else DataResult.success(
                    list
                )
            }
        }

        override fun codec(): Codec<CrusherRecipe> = CODEC

        override fun read(buf: PacketByteBuf): CrusherRecipe {
            val inputs: DefaultedList<Ingredient> = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY)

            for (i in 0 until inputs.size) {
                inputs[i] = Ingredient.fromPacket(buf)
            }

            val output = buf.readItemStack()
            return CrusherRecipe(inputs, output)
        }

        override fun write(buf: PacketByteBuf, recipe: CrusherRecipe) {
            buf.writeInt(recipe.ingredients.size)

            for (ingredient in recipe.ingredients) {
                ingredient.write(buf)
            }

            buf.writeItemStack(recipe.getResult(null))
        }

    }
}
