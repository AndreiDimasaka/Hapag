package com.example.hapag

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hapag.composables.UI.MyRecipeCard
import com.example.hapag.composables.UI.TopReturnBar
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar

class MyFavoritesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MyFavoritesScreen(onBack = { finish() }) // Pass a lambda to handle back navigation
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFavoritesScreen(onBack: () -> Unit) { // Add onBack parameter
    val context = LocalContext.current
    Scaffold(
        topBar = { TopReturnBar(title = "Favorites", arrowBack = false, onNavigateBack = onBack) },
        bottomBar = {
            BottomNavigationBar(onItemSelected = { index ->
                // TODO: Implement navigation based on the selected index
                println("Favorites: Bottom navigation item selected at index: $index")
            }, selectedIndex = 3) // Favorites is at index 3
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
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyFavoritesScreenPreview() {
    AppTheme {
        MyFavoritesScreen(onBack = {}) // Provide an empty lambda for the preview
    }
}