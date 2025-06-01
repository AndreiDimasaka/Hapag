package com.example.hapag.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hapag.Graph
import com.example.hapag.composables.screens.HomeScreen
import com.example.hapag.composables.screens.MyFavoritesScreen
import com.example.hapag.composables.screens.MyRecipesScreen
import com.example.hapag.composables.screens.RecipeScreen
import com.example.hapag.composables.screens.UploadScreen
import com.example.hapag.composables.widgets.TopReturnBar
import com.example.hapag.model.data.Screen
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar
import com.example.hapag.viewModel.RecipeViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Graph.provide(applicationContext)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainScreenNav()
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenNav(){
        val viewModel = viewModel<RecipeViewModel>()
        val navController = rememberNavController()
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route


        Scaffold(
            topBar = {
                if (currentRoute != "Recipe/{recipeId}")
                    TopReturnBar(title = currentRoute.toString(), onClick = { navController.popBackStack()})


            },
            bottomBar = {
                if (currentRoute != "Recipe/{recipeId}")
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
                        viewModel = viewModel
                    )
                }
                composable(Screen.Upload.route) {
                    UploadScreen(
                        navController = navController,
                        paddingValues = paddingValues,
                        viewModel = viewModel
                    )
                }
                composable(
                    route = "Recipe/{recipeId}",
                    arguments = listOf(navArgument("recipeId"){type = NavType.LongType})
                ) {
                    val recipeId = it.arguments?.getLong("recipeId")
                    RecipeScreen(
                        navController = navController,
                        viewModel = viewModel,
                        recipeId = recipeId ,
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
                        viewModel = viewModel
                    )
                }
            }
        }
}


