package com.example.hapag.DummyInputs

import androidx.compose.runtime.Composable
import com.example.hapag.R
import com.example.hapag.composables.RecipeScreen

@Composable
fun LecheFlanRecipeScreen(
    onNavigateBack: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onUploadClick: () -> Unit = {},
    onMyRecipesClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {}
) {
    RecipeScreen(
        recipeTitle = "Leche Flan",
        foodType = "Dessert",
        prepTime = "1 hour 30 mins (Yields to 4)",
        description = "Leche flan is a custard dessert made of eggs, milk, and sugar with a soft caramel topping. It is similar to crème caramel and crème brûlée. It is a staple dessert in the Philippines.",
        ingredients = listOf(
            "10 egg yolks",
            "1 can (14 oz) sweetened condensed milk",
            "1 can (12 oz) evaporated milk",
            "1 teaspoon vanilla extract",
            "For the Caramel:",
            "1 cup granulated sugar",
            "1/4 cup water"
        ),
        procedure = listOf(
            "Prepare the caramel: In a saucepan over medium heat, combine sugar and water. Stir until sugar is dissolved. Bring to a boil and cook without stirring until the syrup turns into a light golden brown caramel. Immediately pour the caramel evenly into the bottom of llaneras (oval molds with lids) or ramekins.",
            "In a large bowl, whisk the egg yolks until light and slightly pale.",
            "Gradually whisk in the sweetened condensed milk and evaporated milk until well combined.",
            "Stir in the vanilla extract.",
            "Pour the custard mixture over the caramel in the llaneras or ramekins. Cover them with their lids or aluminum foil.",
            "Steam the leche flan: Arrange the llaneras in a steamer over simmering water. Steam for 45-60 minutes, or until a toothpick inserted into the center comes out clean.",
            "Alternatively, bake the leche flan: Preheat oven to 350°F (175°C). Place the llaneras in a baking dish and pour hot water into the dish to reach halfway up the sides of the molds (water bath). Bake for 45-60 minutes, or until a toothpick inserted into the center comes out clean.",
            "Once cooked, remove from the steamer or oven and let them cool completely.",
            "Refrigerate for at least 2 hours before serving.",
            "To unmold, run a thin knife around the edge of each llanera. Place a serving plate upside down over the llanera and quickly flip it over. The caramel should drizzle over the custard.",
            "Serve chilled and enjoy!"
        ),
        onNavigateBack = onNavigateBack,
        onAddToFavorites = { /* TODO: Implement adding Leche Flan to favorites */ },
        mainImageResId = R.drawable.lecheflan,
        onHomeClick = onHomeClick,
        onUploadClick = onUploadClick,
        onMyRecipesClick = onMyRecipesClick,
        onFavoritesClick = onFavoritesClick
    )
}