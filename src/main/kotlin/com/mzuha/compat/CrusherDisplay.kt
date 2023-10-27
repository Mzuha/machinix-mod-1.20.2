package com.mzuha.compat

import com.mzuha.recipe.CrusherRecipe
import me.shedaniel.rei.api.common.category.CategoryIdentifier
import me.shedaniel.rei.api.common.display.basic.BasicDisplay
import me.shedaniel.rei.api.common.entry.EntryIngredient
import me.shedaniel.rei.api.common.util.EntryIngredients
import me.shedaniel.rei.api.common.util.EntryStacks
import net.minecraft.recipe.RecipeEntry

class CrusherDisplay(recipe: RecipeEntry<CrusherRecipe>) : BasicDisplay(
    getInputList(recipe.value),
    mutableListOf(
        EntryIngredient.of(
            EntryStacks.of(recipe.value.getResult(null))
        )
    )
) {
    companion object {
        private fun getInputList(crusherRecipe: CrusherRecipe?): MutableList<EntryIngredient> {
            if (crusherRecipe == null) return mutableListOf()
            val list = ArrayList<EntryIngredient>()
            list.add(
                EntryIngredients.ofIngredient(crusherRecipe.ingredients[0])
            )
            return list
        }
    }


    override fun getCategoryIdentifier(): CategoryIdentifier<*> = CrusherCategory.CRUSHING
}