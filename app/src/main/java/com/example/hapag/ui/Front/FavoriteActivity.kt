package com.example.hapag

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Icon
import com.example.hapag.ui.BottomNavigationBar
import androidx.compose.ui.platform.LocalContext

class MyFavoritesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFavoritesScreen(onBack = { finish() }) // Pass a lambda to handle back navigation
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyFavoritesScreen(onBack: () -> Unit) { // Add onBack parameter
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = buttonBackgroundColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = buttonTextColor)
            )
        },
        bottomBar = {
            BottomNavigationBar(onItemSelected = { index ->
                // TODO: Implement navigation based on the selected index
                println("Favorites: Bottom navigation item selected at index: $index")
            }, selectedIndex = 3) // Favorites is at index 3
        },
        containerColor = buttonTextColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Favorites",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = buttonBackgroundColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            FavoriteRecipeCard(
                title = "Recipe 1",
                category = "Savory",
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

@Composable
fun FavoriteRecipeCard(title: String, category: String, modifier: Modifier = Modifier, onRecipeClick: () -> Unit) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable(onClick = onRecipeClick) // Make the entire row clickable
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = buttonBackgroundColor)
            Text("Category: $category", style = MaterialTheme.typography.bodySmall, color = buttonBackgroundColor)
        }
        IconButton(onClick = { /* TODO: Remove from favorites */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Remove from Favorites", tint = buttonBackgroundColor)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyFavoritesScreenPreview() {
    MyFavoritesScreen(onBack = {}) // Provide an empty lambda for the preview
}