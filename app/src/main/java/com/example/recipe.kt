package com.example.hapag

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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

val buttonTextColor = Color(0xFFF1EDE7)
val buttonBackgroundColor = Color(0xFF403A35)

class RecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeScreen(
                recipeTitle = "Recipe Title",
                foodType = "Food Type",
                prepTime = "60 mins",
                description = "Lorem ipsum dolor sit amet. Qui dolorem placeat et nisi iste sit rerum sequi hic cumque magni cum consectetur asperiores.",
                ingredients = List(10) { i -> "Ingredient ${i + 1}" },
                procedure = List(15) { i -> "Procedure ${i + 1}" },
                onNavigateBack = { finish() },
                onAddToFavorites = { /* TODO: Implement adding to favorites */ },
                mainImageResId = R.drawable.ic_launcher_background,
                onHomeClick = { /* TODO: Navigate to Home */ },
                onUploadClick = { /* TODO: Navigate to Upload */ },
                onMyRecipesClick = { /* TODO: Navigate to My Recipes */ },
                onFavoritesClick = { /* TODO: Navigate to Favorites */ }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RecipeScreen(
    recipeTitle: String = "Recipe Title",
    foodType: String = "Food Type",
    prepTime: String = "60 mins",
    description: String = "Lorem ipsum dolor sit amet...",
    ingredients: List<String> = List(10) { i -> "Ingredient ${i + 1}" },
    procedure: List<String> = List(15) { i -> "Procedure ${i + 1}" },
    onNavigateBack: () -> Unit = {},
    onAddToFavorites: () -> Unit = {},
    mainImageResId: Int? = R.drawable.ic_launcher_background,
    onHomeClick: () -> Unit = {},
    onUploadClick: () -> Unit = {},
    onMyRecipesClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {}
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
            NavigationBar(
                tonalElevation = 0.dp
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(24.dp)) },
                    label = { Text("Home", fontSize = 10.sp) },
                    selected = false,
                    onClick = onHomeClick
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Upload", modifier = Modifier.size(24.dp)) },
                    label = { Text("Upload", fontSize = 10.sp) },
                    selected = false,
                    onClick = onUploadClick
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.home_icon), contentDescription = "My Recipes", modifier = Modifier.size(24.dp)) },
                    label = { Text("My Recipes", fontSize = 10.sp) },
                    selected = false,
                    onClick = onMyRecipesClick
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorites", modifier = Modifier.size(24.dp)) },
                    label = { Text("Favorites", fontSize = 10.sp) },
                    selected = false,
                    onClick = onFavoritesClick
                )
            }
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
    }
}