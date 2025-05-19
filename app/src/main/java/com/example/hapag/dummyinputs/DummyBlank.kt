package com.example.hapag.dummyinputs

import androidx.compose.runtime.Composable
import com.example.hapag.composables.screens.RecipeScreen

@Composable
fun BlankRecipeScreen(
    onNavigateBack: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onUploadClick: () -> Unit = {},
    onMyRecipesClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {}
) {
    RecipeScreen(
        navController = TODO(),
        sharedViewModel = TODO(),
        recipe = TODO()
    )
}