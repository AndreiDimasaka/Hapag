package com.example.hapag.model

data class RecipeState(
    val recipes: List<Recipe> = emptyList(),
    val image: ImageData = ImageData.DrawableRes(0),
    val title: String = "",
    val description: String = "",
    val cookTime: String = "",
    val servingSize: Int = 0,
    val category: List<String> = emptyList(),
    val ingredients: List<Item> = emptyList(),
    val instructions: List<Item> = emptyList(),
    val isAddingRecipe: Boolean = false,
    val sortType: SortType = SortType.All
)
