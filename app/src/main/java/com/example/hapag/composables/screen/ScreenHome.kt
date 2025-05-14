package com.example.hapag.composables.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.hapag.RecipeActivity
import com.example.hapag.buttonBackgroundColor
import com.example.hapag.composables.FigmaDashboardLayout
import com.example.hapag.composables.TopReturnBar
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    Scaffold(
        bottomBar = {
            BottomNavigationBar(onItemSelected = { index ->
                println("Main Activity: Bottom navigation item selected at index: $index")
            }, selectedIndex = 0)
        },
        topBar = {
            TopReturnBar(title = "Home", onNavigateBack = {}, arrowBack = false)
        }
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            val dashboard = createRef()
            FigmaDashboardLayout(
                modifier = Modifier.constrainAs(dashboard) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                buttonBackgroundColor = buttonBackgroundColor,
                onRecipeClick = { recipeName ->
                    val formattedRecipeName = recipeName.lowercase().replace(" ", "").replace("-", "")
                    val intent = Intent(context, RecipeActivity::class.java).apply {
                        putExtra("recipe", formattedRecipeName)
                    }
                    context.startActivity(intent)
                }
            )
        }
    }
}
