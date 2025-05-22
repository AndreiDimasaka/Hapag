package com.example.hapag.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hapag.composables.screens.HomeScreen
import com.example.hapag.composables.screens.MyFavoritesScreen
import com.example.hapag.composables.screens.MyRecipesScreen
import com.example.hapag.composables.screens.RecipeScreen
import com.example.hapag.composables.screens.SearchScreen
import com.example.hapag.composables.screens.UploadScreen
import com.example.hapag.composables.widgets.TopReturnBar
import com.example.hapag.model.Screen
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar
import com.example.hapag.viewModel.SharedViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainScreenNav()
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun MainScreenNav(){
        val viewModel = viewModel<SharedViewModel>()
        val myRecipeList = viewModel.myRecipeList.collectAsState()
        val myFavoriteList = viewModel.myFavoriteList.collectAsState()
        val recipeList = viewModel.recipeList.collectAsState()
        val navController = rememberNavController()
        val currentRoute = currentRoute(navController)
        Scaffold(
            topBar = {
                if (currentRoute != Screen.Recipe.route && currentRoute != "myRecipes/{recipeTitle}" && currentRoute != "myFavorites/{recipeTitle}")
                    TopReturnBar(title = currentRoute.toString(), onClick = { navController.popBackStack()})


            },
            bottomBar = {
                if (currentRoute != Screen.Recipe.route && currentRoute != "myRecipes/{recipeTitle}" && currentRoute != "myFavorites/{recipeTitle}")
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
                composable(
                    route = Screen.Recipe.route,
                    arguments = listOf(navArgument("recipeTitle"){type = NavType.StringType})
                ) {
                    val recipeTitle = it.arguments?.getString("recipeTitle")
                    val recipe = recipeList.value.find { it.title == recipeTitle }

                    RecipeScreen(
                        navController = navController,
                        sharedViewModel = viewModel,
                        recipe = recipe,
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
                composable(
                    route = "myRecipes/{recipeTitle}",
                    arguments = listOf(navArgument("recipeTitle"){type = NavType.StringType})
                ) {
                    val recipeTitle = it.arguments?.getString("recipeTitle")
                    val recipe = myRecipeList.value.find { it.title == recipeTitle }
                    RecipeScreen(
                        navController = navController,
                        sharedViewModel = viewModel,
                        recipe = recipe,
                    )
                }
                    composable(
                        route = "myFavorites/{recipeTitle}",
                        arguments = listOf(navArgument("recipeTitle"){type = NavType.StringType})
                    ) {
                        val recipeTitle = it.arguments?.getString("recipeTitle")
                        val recipe = myFavoriteList.value.find { it?.title == recipeTitle }
                        RecipeScreen(
                            navController = navController,
                            sharedViewModel = viewModel,
                            recipe = recipe,
                        )
                }
                composable(route = "Search")
                {
                    SearchScreen(
                        navController = navController,
                        viewModel = viewModel,
                        paddingValues = paddingValues
                    )
                }
            }
        }
}


