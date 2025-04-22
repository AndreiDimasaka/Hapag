package com.example.hapag

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.hapag.ui.BottomNavigationBar

val buttonTextColor = Color(0xFFF1EDE7)
val buttonBackgroundColor = Color(0xFF403A35)

class RecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val recipeName = intent.getStringExtra("recipe")
            RecipeContent(recipeName = recipeName) { finish() }
        }
    }
}

@Composable
fun RecipeContent(recipeName: String?, onNavigateBack: () -> Unit) {
    if (recipeName == "halohalo") {
        HaloHaloRecipeScreen(onNavigateBack = onNavigateBack)
    } else if (recipeName == "lecheflan") {
        LecheFlanRecipeScreen(onNavigateBack = onNavigateBack)
    }
    // Add more conditions here for other recipes
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    recipeTitle: String,
    foodType: String,
    prepTime: String,
    description: String,
    ingredients: List<String>,
    procedure: List<String>,
    onNavigateBack: () -> Unit,
    onAddToFavorites: () -> Unit,
    mainImageResId: Int?,
    onHomeClick: () -> Unit,
    onUploadClick: () -> Unit,
    onMyRecipesClick: () -> Unit,
    onFavoritesClick: () -> Unit
) {
    var showFullScreenImage by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = buttonBackgroundColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = buttonTextColor)
            )
        },
        bottomBar = {
            BottomNavigationBar(onItemSelected = { index ->
                when (index) {
                    0 -> onHomeClick()
                    1 -> onUploadClick()
                    2 -> onMyRecipesClick()
                    3 -> onFavoritesClick()
                }
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(buttonTextColor)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            if (mainImageResId != null) {
                Image(
                    painter = painterResource(id = mainImageResId),
                    contentDescription = recipeTitle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clickable { showFullScreenImage = true },
                    contentScale = ContentScale.Crop
                )
                AnimatedVisibility(
                    visible = showFullScreenImage,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Dialog(onDismissRequest = { showFullScreenImage = false }) {
                        Box(
                            Modifier.fillMaxSize()
                        ) {
                            if (mainImageResId != null) {
                                Image(
                                    painter = painterResource(
                                        id = mainImageResId
                                    ),
                                    contentDescription = recipeTitle,
                                    modifier = Modifier.fillMaxSize()
                                        .clickable { showFullScreenImage = false },
                                    contentScale = ContentScale.Fit
                                )
                                IconButton(
                                    onClick = { showFullScreenImage = false },
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(8.dp)
                                ) {
                                    Icon(
                                        Icons.Filled.Close,
                                        contentDescription = "Close",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
            RecipeDetails(
                recipeTitle = recipeTitle,
                foodType = foodType,
                prepTime = prepTime,
                description = description,
                ingredients = ingredients,
                procedure = procedure,
                onAddToFavorites = onAddToFavorites
            )
        }
    }
}

@Composable
fun RecipeDetails(
    recipeTitle: String,
    foodType: String,
    prepTime: String,
    description: String,
    ingredients: List<String>,
    procedure: List<String>,
    onAddToFavorites: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            recipeTitle,
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp),
            color = buttonBackgroundColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 4.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                foodType,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = buttonBackgroundColor
            )
            Text(
                prepTime,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = buttonBackgroundColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .clickable { onAddToFavorites() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                Icons.Filled.FavoriteBorder,
                contentDescription = "Add to Favorites",
                tint = buttonBackgroundColor,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Add to Favorites",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                color = buttonBackgroundColor
            )
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Description",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
            color = buttonBackgroundColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(description, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = buttonBackgroundColor)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)
        Text(
            "Ingredients",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
            color = buttonBackgroundColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        ingredients.forEach { ingredient ->
            Text(
                "- $ingredient",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                color = buttonBackgroundColor,
                modifier = Modifier.padding(vertical = 1.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Procedure",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
            color = buttonBackgroundColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column {
            procedure.forEachIndexed { index, step ->
                Text(
                    "${index + 1}. $step",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                    color = buttonBackgroundColor,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

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
        mainImageResId = R.drawable.halohalo, // Replace with your actual image resource
        onHomeClick = onHomeClick,
        onUploadClick = onUploadClick,
        onMyRecipesClick = onMyRecipesClick,
        onFavoritesClick = onFavoritesClick
    )
}

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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHaloHaloRecipeScreen() {
    HaloHaloRecipeScreen(onNavigateBack = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLecheFlanRecipeScreen() {
    LecheFlanRecipeScreen(onNavigateBack = {})
}