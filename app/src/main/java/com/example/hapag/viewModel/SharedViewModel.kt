package com.example.hapag.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hapag.data.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class sharedViewModel: ViewModel() {
    private val _selectedRecipe = MutableStateFlow<Recipe?>(null)
    val selectedRecipe: StateFlow<Recipe?> = _selectedRecipe.asStateFlow()

    private val _recipeList = mutableListOf<Recipe>()
    val recipeList: List<Recipe> = _recipeList

    private val _myRecipeList = mutableListOf<Recipe>()
    val myRecipeList: List<Recipe> = _myRecipeList

    private val _myFavoriteList = mutableListOf<Recipe>()
    val myFavoriteList: List<Recipe> = _myFavoriteList

    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }

    fun addToRecipe(recipe: Recipe) {
        _recipeList.add(recipe)
    }

    fun addToMyRecipe(recipe: Recipe) {
        _myRecipeList.add(recipe)
    }

    fun addToMyFavorite(recipe: Recipe) {
        _myFavoriteList.add(recipe)
    }


}