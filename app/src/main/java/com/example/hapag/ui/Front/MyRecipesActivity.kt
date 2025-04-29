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
import com.example.hapag.ui.BottomNavigationBar // Import
import androidx.compose.ui.platform.LocalContext

class MyRecipesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRecipesScreen(onBack = { finish() }) // Pass the finish() lambda
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRecipesScreen(onBack: () -> Unit) { // Add the onBack parameter
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = buttonBackgroundColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) { // Call the onBack lambda
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = buttonBackgroundColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = buttonTextColor)
            )
        },
        bottomBar = {
            BottomNavigationBar(onItemSelected = { index ->
                // TODO: Implement navigation based on the selected index
                println("My Recipes: Bottom navigation item selected at index: $index")
            }, selectedIndex = 2) // My Recipes is at index 2
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
                "My Recipes",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = buttonBackgroundColor
            )
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

@Composable
fun MyRecipeCard(title: String, category: String, modifier: Modifier = Modifier, onRecipeClick: () -> Unit) {
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
        IconButton(onClick = { /* TODO: Remove recipe */ }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Remove Recipe", tint = buttonBackgroundColor)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyRecipesScreenPreview() {
    MyRecipesScreen(onBack = {})
}