package com.example.hapag.ViewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hapag.data.Recipe


class sharedViewModel: ViewModel() {
    private val _selectedRecipe = mutableStateOf<Recipe?>(null)
    val selectedRecipe: State<Recipe?> = _selectedRecipe

    fun selectRecipe(recipe: Recipe) {
        _selectedRecipe.value = recipe
    }
}