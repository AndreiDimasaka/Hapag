package com.example.hapag.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hapag.composables.screens.HomeScreen
import com.example.hapag.composables.screens.MyFavoritesScreen
import com.example.hapag.composables.screens.MyRecipesScreen
import com.example.hapag.composables.screens.RecipeScreen
import com.example.hapag.composables.screens.UploadScreen
import com.example.hapag.composables.widgets.TopReturnBar
import com.example.hapag.data.Screen
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hapag.viewModel.sharedViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val viewModel = viewModel<sharedViewModel>()
                val navController = rememberNavController()
                val currentRoute = currentRoute(navController)
                Scaffold(
                    topBar = {
                        if (currentRoute != Screen.Recipe.route )
                            TopReturnBar(title = currentRoute.toString() , onNavigateBack = {}, arrowBack = false)
                    },
                    bottomBar = {
                        if (currentRoute != Screen.Recipe.route )
                        BottomNavigationBar(navController)
                    }
                )
                { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable(Screen.Home.route) {
                                HomeScreen(
                                    navController = navController,
                                    paddingValues = paddingValues,
                                    sharedViewModel = viewModel
                                )
                            }
                            composable(Screen.Upload.route) {
                                UploadScreen(
                                    navController = navController,
                                    paddingValues = paddingValues,
                                    sharedViewModel = viewModel
                                )
                            }
                            composable(Screen.Recipe.route) {
                                RecipeScreen(
                                    navController = navController,
                                    sharedViewModel = viewModel
                                )
                            }
                            composable(Screen.MyRecipes.route){
                                MyRecipesScreen(
                                    navController = navController,
                                    viewModel = viewModel,
                                    paddingValues = paddingValues
                                )
                            }
                            composable(Screen.Favorites.route){
                                MyFavoritesScreen(
                                    navController = navController,
                                    paddingValues = paddingValues,
                                    sharedViewModel = viewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


