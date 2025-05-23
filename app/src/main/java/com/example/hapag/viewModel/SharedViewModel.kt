package com.example.hapag.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapag.model.DummyDataViewModel
import com.example.hapag.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


@Suppress("UNCHECKED_CAST")
class SharedViewModel: ViewModel() {

    private val _recipeList = MutableStateFlow<List<Recipe>>(emptyList())
    val recipeList: StateFlow<List<Recipe>> = _recipeList

    init {
        val dummyRecipes = DummyDataViewModel().recipesDummy
        _recipeList.value = dummyRecipes
    }

    var searchQuery by mutableStateOf("")
        private set

    val searchResults: StateFlow<List<Recipe>> =
        snapshotFlow { searchQuery } .combine(_recipeList) { query, recipes ->
            when {
                searchQuery.isNotEmpty() -> recipes.filter { recipe ->
                    recipe.title.contains(searchQuery, ignoreCase = true)
                }
                else -> recipes
            }
        } .stateIn(
            scope = viewModelScope,
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000)
        )

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    private val _myRecipeList = MutableStateFlow<List<Recipe>>(emptyList())
    val myRecipeList: StateFlow<List<Recipe>> = _myRecipeList

    private val _myFavoriteList = MutableStateFlow<List<Recipe?>>(emptyList())
    val myFavoriteList: StateFlow<List<Recipe?>> = _myFavoriteList.asStateFlow()

    fun addToRecipeList(recipe: Recipe) {
        _recipeList.update { currentList ->
            currentList + recipe
        }
    }

    fun addToMyRecipe(recipe: Recipe) {
        _myRecipeList.update { currentList ->
            currentList + recipe
        }
    }



    fun toggleMyFavorite(recipe: Recipe?) {
        _myFavoriteList.update { currentList ->
            if (currentList.contains(recipe)) {
                currentList - recipe
            } else {
                currentList + recipe
            }
        }
    }

}