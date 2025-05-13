package com.example.hapag.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class RecipeUiState(
    val recipeTitle: String = "",
    val foodType: String = "",
    val prepTime: String = "",
    val description: String = "",
    val ingredients: List<String> = emptyList(),
    val procedure: List<String> = emptyList(),
    val mainImageResId: Int? = null,
    val isFullScreenImageVisible: Boolean = false,
    val isFavorite: Boolean = false
)

class RecipeScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(RecipeUiState())
    val uiState: StateFlow<RecipeUiState> = _uiState.asStateFlow()


    fun setRecipeData(
        recipeTitle: String,
        foodType: String,
        prepTime: String,
        description: String,
        ingredients: List<String>,
        procedure: List<String>,
        mainImageResId: Int?
    ) {
        _uiState.value = RecipeUiState(
            recipeTitle = recipeTitle,
            foodType = foodType,
            prepTime = prepTime,
            description = description,
            ingredients = ingredients,
            procedure = procedure,
            mainImageResId = mainImageResId
        )
    }

    // Toggle full-screen image visibility
    fun toggleFullScreenImage() {
        _uiState.value = _uiState.value.copy(
            isFullScreenImageVisible = !_uiState.value.isFullScreenImageVisible
        )
    }

    // Handle adding to favorites
    fun addToFavorites() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isFavorite = true)
        }
    }
}