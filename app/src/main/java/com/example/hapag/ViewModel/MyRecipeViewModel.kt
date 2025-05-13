package com.example.hapag.ViewModel

import androidx.lifecycle.ViewModel
import com.example.hapag.data.Recipe

class MyRecipeViewModel: ViewModel() {

    val myRecipeList = mutableListOf<Recipe>(
    )

    fun addMyRecipe(recipe: Recipe) {
        myRecipeList.add(recipe)
    }
}