package com.example.hapag

import UploadScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hapag.ViewModel.RecipeViewModel
import com.example.hapag.composables.screen.FavoritesScreen
import com.example.hapag.composables.screen.HomeScreen
import com.example.hapag.composables.screen.MyRecipesScreen
import com.example.hapag.composables.screen.RecipeScreen
import com.example.hapag.data.Recipe
import com.example.hapag.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {

            }
        }
    }
}


    @Composable
    fun Navigation() {
        val recipeViewModel = viewModel<RecipeViewModel>()
        val navController = rememberNavController()
        var selectedIndex by remember { mutableIntStateOf(0) } // Track the selected tab


        val routes = listOf("home", "upload", "myRecipes", "favorite")

        Box(modifier = Modifier.fillMaxSize()) {
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.fillMaxSize()
            ) {
                composable("home") {
                    HomeScreen(navController = navController)
                }
                composable("upload") {
                    UploadScreen(navController = navController)
                }
                composable("myRecipes") {
                    MyRecipesScreen(
                        navController = navController,
                        onBack = { navController.navigateUp() },
                        recipeViewModel = recipeViewModel
                    )
                }
                composable("favorite") {
                    FavoritesScreen(
                        navController = navController,
                        onBack = { navController.navigateUp() },
                        recipeViewModel = recipeViewModel
                    )
                }
                composable(
                    route = "recipe/{recipe}",
                    arguments = listOf(
                        navArgument("recipe") {
                            type = androidx.navigation.NavType.ParcelableType(Recipe::class.java)
                        }
                    )
                ) { backStackEntry ->
                    val recipe = backStackEntry.arguments?.getParcelable<Recipe>("recipe")
                    recipe?.let {
                        RecipeScreen(
                            navController = navController,
                            recipe = it,
                            recipeViewModel = recipeViewModel
                        )
                    } ?: run {
                        // Handle null case (e.g., navigate back)
                        navController.navigateUp()
                    }
                }
            }

            // Bottom Navigation Bar
            NavigationBar(
                modifier = Modifier.fillMaxWidth()
            ) {
                routes.forEachIndexed { index, route ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(route) {
                                // Prevent stacking same destination
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            // Replace with actual icons (e.g., from Icons.Default)
                            Text(route[0].uppercase())
                        },
                        label = { Text(route) }
                    )
                }
            }
        }
    }




@Preview
@Composable
fun MainActivityScreenPreview() {
    AppTheme {

    }
}