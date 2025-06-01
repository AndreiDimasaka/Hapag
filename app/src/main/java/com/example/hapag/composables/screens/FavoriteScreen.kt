package com.example.hapag.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hapag.composables.widgets.MyRecipeCard
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.RecipeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFavoritesScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: RecipeViewModel
) {
    val favoriteList by viewModel.favoritesWithCategories.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.loadFavorites()
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(paddingValues),
        verticalArrangement = if (favoriteList.isEmpty()) Arrangement.Center else Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ) {
        if (favoriteList.isNotEmpty()) {
            items(items = favoriteList) { recipeWithCategories ->

                MyRecipeCard(
                    title = recipeWithCategories.recipe.title,
                    category = recipeWithCategories.categories.map { it.name },
                    modifier = Modifier.padding(12.dp),
                    onRecipeClick = {
                        navController.navigate("Recipe/${recipeWithCategories.recipe.id}")
                    },
                    onRemoveClick = {
                        viewModel.toggleFavorite(recipeWithCategories.recipe.id, false)
                    },
                    icon = {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Remove Recipe",
                            tint = AppTheme.colorScheme.secondary
                        )
                    }
                )
            }
        }  else{
            item {
                Text(
                    text = "You don't have any favorites",
                    color = Color.Gray,
                    style = AppTheme.typography.labelLarge
                )
                Button(
                    onClick = { navController.navigate("Home") },
                    modifier = Modifier.padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppTheme.colorScheme.background
                    ),
                    border = null
                ) {
                    androidx.compose.material3.Text(
                        text = "Home",
                        color = AppTheme.colorScheme.secondary,
                        textDecoration = TextDecoration.Underline,
                        style = AppTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

