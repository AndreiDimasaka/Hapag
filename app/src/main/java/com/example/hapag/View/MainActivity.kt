package com.example.hapag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hapag.composables.Screens.HomeScreen
import com.example.hapag.composables.UI.TopReturnBar
import com.example.hapag.data.Screen
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar
import androidx.compose.runtime.getValue
import com.example.hapag.composables.Screens.UploadScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
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
                    Surface(modifier = Modifier.padding(paddingValues)) {
                        NavHost(
                            navController = navController,
                            startDestination = "home"
                        ) {
                            composable(Screen.Home.route) {
                                HomeScreen(
                                    navController = navController,
                                    paddingValues = paddingValues
                                )
                            }
                            composable(Screen.Upload.route) {
                                UploadScreen(
                                    navController = navController,
                                    paddingValues = paddingValues
                                )
                            }
                            composable(Screen.Recipe.route) {

                            }
                            composable(Screen.MyRecipes.route){

                            }
                            composable(Screen.Favorites.route){

                            }
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

@Composable
fun Navigation(){

}


