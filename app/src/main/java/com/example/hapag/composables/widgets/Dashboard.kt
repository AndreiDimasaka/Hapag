package com.example.hapag.composables.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hapag.composables.screens.SearchScreen
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.RecipeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FigmaDashboardLayout(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: RecipeViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.loadFilteredRecipes()
    }

    val filteredRecipes by viewModel.filteredRecipes.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val searchResults = viewModel.searchResults.collectAsState()




    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 10.dp),
        horizontalAlignment = Alignment.Start
    ) {

            SearchScreen(
                searchQuery = searchQuery  ,
                searchResults = searchResults.value ,
                onSearchQueryChange = { viewModel.onSearchQueryChange(it) },
                navController = navController,
            )
           Spacer(modifier = Modifier.height(15.dp))
           Text(
                text = "Category",
                color = AppTheme.colorScheme.onBackground,
                style = AppTheme.typography.labelLarge,

            )

            Spacer(modifier = Modifier.height(8.dp))
            FilterButton(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = AppTheme.colorScheme.secondary.copy(0.7f), thickness = 3.dp)
            Spacer(modifier = Modifier.height(10.dp))
            FilterRow(
                modifier = Modifier.fillMaxWidth(),
                viewModel = viewModel
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = AppTheme.colorScheme.secondary.copy(0.7f), thickness = 3.dp)
            Spacer(modifier = Modifier.height(10.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    filteredRecipes,
                    key = { it.recipe.id!! }
                ) { recipeWithCategories ->
                    RecipeCard(
                        recipeWithCategories = recipeWithCategories,
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            navController.navigate("Recipe/${recipeWithCategories.recipe.id}")
                        }
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun FigmaDashboardLayoutPreview() {
        AppTheme {
            Surface(
                color = AppTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
            }
        }
    }



