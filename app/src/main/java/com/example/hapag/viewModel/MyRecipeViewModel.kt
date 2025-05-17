package com.example.hapag.viewModel

import androidx.lifecycle.ViewModel
import com.example.hapag.data.Recipe

class RecipeViewModel: ViewModel() {

    val myRecipeList = mutableListOf<Recipe>(
    )

    fun addMyRecipe(recipe: Recipe) {
        myRecipeList.add(recipe)
    }
}