package com.example.hapag.composables.screen

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hapag.RecipeActivity
import com.example.hapag.ViewModel.RecipeViewModel
import com.example.hapag.composables.MyRecipeCard
import com.example.hapag.composables.TopReturnBar
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRecipesScreen(onBack: () -> Unit,
                    navController: NavController,
                    recipeViewModel: RecipeViewModel
) { // Add the onBack parameter
    val recipeViewModel = recipeViewModel
    val context = LocalContext.current
    Scaffold(
        topBar = { TopReturnBar(title = "My Recipes", arrowBack = false,) },
        bottomBar = {
            BottomNavigationBar(onItemSelected = { index ->
                // TODO: Implement navigation based on the selected index
                println("My Recipes: Bottom navigation item selected at index: $index")
            }, selectedIndex = 2) // My Recipes is at index 2
        },
        containerColor = AppTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            MyRecipeCard(
                title = "My Recipe 1",
                category = "Sweet",
                modifier = Modifier.fillMaxWidth(),
                onRecipeClick = {
                    val intent = Intent(context, RecipeActivity::class.java).apply {
                        putExtra("recipe", "blankrecipe")
                    }
                    context.startActivity(intent)
                }
            )
            viewModel.myRecipeList.forEach{
                MyRecipeCard(
                    title = it.title,
                    category = it.category,
                    modifier = Modifier.fillMaxWidth(),
                    onRecipeClick = {
                        val intent = Intent(context, RecipeActivity::class.java).apply {
                            putExtra("recipe", it.title)
                        }
                        context.startActivity(intent) }
                )
            }
        }
    }
}