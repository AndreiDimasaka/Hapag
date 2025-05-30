package com.example.hapag.composables.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.hapag.composables.widgets.FigmaDashboardLayout
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.RecipeViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: RecipeViewModel
) {

    AppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = AppTheme.colorScheme.background
        ) {
            FigmaDashboardLayout(
                modifier = Modifier,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

