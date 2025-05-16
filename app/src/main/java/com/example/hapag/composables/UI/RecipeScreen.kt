package com.example.hapag.composables.UI

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar

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
    onHomeClick: () -> Unit = {},
    onUploadClick: () -> Unit = {},
    onMyRecipesClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {}
) {
    var showFullScreenImage by remember { mutableStateOf(false) }
    AppTheme {
        Scaffold(
            topBar = { TopReturnBar(arrowBack = true, title = "", onNavigateBack = onNavigateBack)},
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
                    .background(AppTheme.colorScheme.background)
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
}

@Preview
@Composable
fun PreviewRecipeScreen() {
    RecipeScreen(
        recipeTitle = "Delicious Pasta",
        foodType = "Italian",
        prepTime = "30 mins",
        description = "A simple yet delicious pasta recipe.",
        ingredients = listOf("Pasta", "Tomato Sauce", "Cheese"),
        procedure = listOf("Cook pasta", "Add sauce", "Add cheese"),
        onNavigateBack = {},
        onAddToFavorites = {},
        mainImageResId = null // You can provide a sample image resource ID here if needed
    )
}

