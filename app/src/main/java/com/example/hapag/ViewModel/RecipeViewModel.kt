package com.example.hapag.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.hapag.data.Recipe


class RecipeViewModel: ViewModel() {

    val recipes = mutableStateListOf<Recipe>()
    val favoriteRecipes = mutableStateListOf<Recipe>()
    val myRecipes = mutableStateListOf<Recipe>()

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun addMyRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun isFavorite(recipe: Recipe): Boolean {
        return favoriteRecipes.contains(recipe)
    }

    fun toggleFavorite(recipe: Recipe) {
        if (favoriteRecipes.contains(recipe)) {
            favoriteRecipes.remove(recipe)
        } else {
            favoriteRecipes.add(recipe)
        }
    }

}