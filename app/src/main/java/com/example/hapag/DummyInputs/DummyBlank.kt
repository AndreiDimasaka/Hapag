package com.example.hapag.DummyInputs

import androidx.compose.runtime.Composable
import com.example.hapag.R
import com.example.hapag.RecipeScreen

@Composable
fun BlankRecipeScreen(
    onNavigateBack: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onUploadClick: () -> Unit = {},
    onMyRecipesClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {}
) {
    RecipeScreen(
        recipeTitle = "Recipe Title",
        foodType = "Sweet Savory",
        prepTime = "N/A",
        description = "Decoy Recipe.",
        ingredients = listOf(
            "Ingredient 1",
            "Ingredient 2",
            "Ingredient 3"
        ),
        procedure = listOf(
            "Procedure 1",
            "Procedure 2",
            "Procedure 3"
        ),
        onNavigateBack = onNavigateBack,
        onAddToFavorites = { /* TODO: Implement adding Blank Recipe to favorites */ },
        mainImageResId = R.drawable.ic_launcher_background,
        onHomeClick = onHomeClick,
        onUploadClick = onUploadClick,
        onMyRecipesClick = onMyRecipesClick,
        onFavoritesClick = onFavoritesClick
    )
}