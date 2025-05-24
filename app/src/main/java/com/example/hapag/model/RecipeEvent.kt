package com.example.hapag.model

sealed interface RecipeEvent {
    object SaveRecipe: RecipeEvent
    data class SetImage(val image: ImageData): RecipeEvent
    data class SetTitle(val title: String): RecipeEvent
    data class SetDescription(val description: String): RecipeEvent
    data class SetCookTime(val cookTime: String): RecipeEvent
    data class SetServingSize(val servingSize: Int): RecipeEvent
    data class SetCategory(val category: List<String>): RecipeEvent
    data class SetIngredients(val ingredients: List<Item>): RecipeEvent
    data class SetInstructions(val instructions: List<Item>): RecipeEvent

    data class DeleteRecipe(val recipe: Recipe): RecipeEvent

    data class SortRecipes(val sortType: SortType): RecipeEvent

}