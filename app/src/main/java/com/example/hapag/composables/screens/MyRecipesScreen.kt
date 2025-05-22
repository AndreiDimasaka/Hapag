package com.example.hapag.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hapag.composables.widgets.MyRecipeCard
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.SharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRecipesScreen(
    navController: NavController,
    viewModel: SharedViewModel = viewModel(),
    paddingValues: PaddingValues
) {
    val myRecipes by viewModel.myRecipeList.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp)
    ){
        items(
            items = myRecipes){ recipe ->
            MyRecipeCard(
                title = recipe.title,
                category = recipe.category,
                modifier = Modifier.padding(12.dp),
                onRecipeClick = {
                    navController.navigate("myRecipes/${recipe.title}")
                }
            )
        }
    }
}

