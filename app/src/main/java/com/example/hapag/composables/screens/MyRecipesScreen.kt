package com.example.hapag.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
fun MyRecipesScreen(
    navController: NavController,
    viewModel: RecipeViewModel,
    paddingValues: PaddingValues
) {
    AppTheme {
        val myRecipes by viewModel.userRecipesWithCategories.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.loadUserRecipes()
        }


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = if (myRecipes.isEmpty()) Arrangement.Center else Arrangement.Top,
            contentPadding = PaddingValues(16.dp)
        ) {
            if (myRecipes.isNotEmpty()) {
                items(
                    items = myRecipes
                ) { recipeWithCategories ->
                    MyRecipeCard(
                        title = recipeWithCategories.recipe.title,
                        category = recipeWithCategories.categories.map { it.name },
                        modifier = Modifier.padding(12.dp),
                        onRecipeClick = {
                            navController.navigate("Recipe/${recipeWithCategories.recipe.id}")
                        },
                        onRemoveClick = {
                            viewModel.deleteCompleteRecipe(recipeWithCategories.recipe.id)
                        },
                        icon = {
                            Icon(
                                Icons.Filled.Clear,
                                contentDescription = "Remove Recipe",
                                tint = AppTheme.colorScheme.secondary
                            )
                        }
                    )
                }
            } else {
                item {
                    Text(
                        text = "You don't have any recipes yet!",
                        color = Color.Gray,
                        style = AppTheme.typography.labelLarge
                    )
                    Button(
                        onClick = { navController.navigate("Upload") },
                        modifier = Modifier.padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppTheme.colorScheme.background
                        ),
                        border = null
                    ) {
                        Text(
                            text = "Upload Recipe",
                            color = AppTheme.colorScheme.secondary,
                            textDecoration = TextDecoration.Underline,
                            style = AppTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}

