package com.example.hapag.DummyInputs

import androidx.compose.runtime.Composable
import com.example.hapag.R
import com.example.hapag.RecipeScreen

@Composable
fun HaloHaloRecipeScreen(
    onNavigateBack: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onUploadClick: () -> Unit = {},
    onMyRecipesClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {}
) {
    RecipeScreen(
        recipeTitle = "Halo-Halo",
        foodType = "Dessert",
        prepTime = "30 mins (Yields to 2)",
        description = "Halo-halo is a popular Filipino cold dessert made up of mixtures of shaved ice and milk to which are added various boiled sweet beans, fruits, and jellies, and then topped with leche flan, purple yam jam (ube halaya), ube ice cream, or leche flan, and often drizzled with caramelized sugar.",
        ingredients = listOf(
            "1 cup shaved ice",
            "1/4 cup cooked sweet beans (e.g., kidney beans, garbanzos)",
            "1/4 cup sweetened banana (minatamis na saging)",
            "1/4 cup jackfruit (langka), sliced",
            "2 tablespoons nata de coco (coconut gel)",
            "2 tablespoons kaong (sugar palm fruit)",
            "2 tablespoons leche flan, cubed",
            "2 tablespoons ube halaya (purple yam jam)",
            "2 tablespoons ube ice cream",
            "2 tablespoons pinipig (toasted pounded young rice)",
            "Sweetened milk, to taste"
        ),
        procedure = listOf(
            "In a tall glass, layer the sweet beans, sweetened banana, jackfruit, nata de coco, and kaong.",
            "Fill the glass with shaved ice.",
            "Pour sweetened milk over the shaved ice.",
            "Top with leche flan, ube halaya, and ube ice cream.",
            "Sprinkle with pinipig.",
            "Serve immediately and enjoy!"
        ),
        onNavigateBack = onNavigateBack,
        onAddToFavorites = { /* TODO: Implement adding Halo-Halo to favorites */ },
        mainImageResId = R.drawable.halohalo,
        onHomeClick = onHomeClick,
        onUploadClick = onUploadClick,
        onMyRecipesClick = onMyRecipesClick,
        onFavoritesClick = onFavoritesClick
    )
}