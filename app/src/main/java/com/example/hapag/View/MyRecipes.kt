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
import com.example.hapag.DummyInputs.BlankRecipeScreen
import com.example.hapag.DummyInputs.HaloHaloRecipeScreen
import com.example.hapag.DummyInputs.LecheFlanRecipeScreen
import com.example.hapag.composables.RecipeContent
import com.example.hapag.composables.RecipeDetails
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar

val buttonTextColor = Color(0xFFF1EDE7)
val buttonBackgroundColor = Color(0xFF403A35)

class RecipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val recipeName = intent.getStringExtra("recipe")
                RecipeContent(recipeName = recipeName) { finish() }
            }
        }
    }
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
    onHomeClick: () -> Unit = {},
    onUploadClick: () -> Unit = {},
    onMyRecipesClick: () -> Unit = {},
    onFavoritesClick: () -> Unit = {}
) {
    var showFullScreenImage by remember { mutableStateOf(false) }
    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { /* Title is now below the image */ },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = AppTheme.colorScheme.onSecondary
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBlankRecipeScreen() {
    BlankRecipeScreen(onNavigateBack = {})
}