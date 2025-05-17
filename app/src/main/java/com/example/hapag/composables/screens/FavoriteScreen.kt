package com.example.hapag.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hapag.composables.widgets.MyRecipeCard
import com.example.hapag.data.Screen
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.sharedViewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFavoritesScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    sharedViewModel: sharedViewModel = viewModel()
) {
    val recipe by sharedViewModel.selectedRecipe.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        if (!sharedViewModel.myFavoriteList.isEmpty()) {
            MyRecipeCard(
                title = recipe?.title ?: "",
                category = recipe?.category ?: "",
                modifier = Modifier.fillMaxWidth(),
                onRecipeClick = {
                    navController.navigate("${Screen.Recipe.route}/${recipe?.title}")
                }
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyFavoritesScreenPreview() {
    AppTheme {
        MyFavoritesScreen(
            navController = TODO(),
            paddingValues = TODO(),
            sharedViewModel = TODO()
        ) // Provide an empty lambda for the preview
    }
}