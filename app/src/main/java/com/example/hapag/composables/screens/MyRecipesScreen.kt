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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hapag.composables.widgets.MyRecipeCard
import com.example.hapag.data.Screen
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.sharedViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRecipesScreen(
                    navController: NavController,
                    viewModel: sharedViewModel = viewModel(),
                    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val uploadedRecipe by viewModel.selectedRecipe.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (uploadedRecipe != null) {
            Spacer(modifier = Modifier.height(16.dp))
            MyRecipeCard(
                title = uploadedRecipe?.title ?: "",
                category = uploadedRecipe?.category ?: "",
                modifier = Modifier.fillMaxWidth(),
                onRecipeClick = {
                    navController.navigate("${Screen.Recipe.route}/${uploadedRecipe?.title}")
                }
            )
        }
    }
}

