package com.example.hapag

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapag.ui.BaseActivity

class MyRecipesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyRecipesScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRecipesScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Navigate back */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = buttonBackgroundColor)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = buttonTextColor)
            )
        },
        bottomBar = {
            NavigationBar(tonalElevation = 0.dp) {
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.home_icon), contentDescription = "Home", modifier = Modifier.size(24.dp), tint = buttonBackgroundColor) },
                    label = { Text("Home", fontSize = 10.sp, color = buttonBackgroundColor) },
                    selected = false,
                    onClick = { /* TODO: Navigate to Home */ }
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.home_icon), contentDescription = "Upload", modifier = Modifier.size(24.dp), tint = buttonBackgroundColor) },
                    label = { Text("Upload", fontSize = 10.sp, color = buttonBackgroundColor) },
                    selected = false,
                    onClick = { /* TODO: Navigate to Upload */ }
                )
                NavigationBarItem(
                    icon = { Icon(painterResource(id = R.drawable.home_icon), contentDescription = "My Recipes", modifier = Modifier.size(24.dp), tint = buttonBackgroundColor) },
                    label = { Text("My Recipes", fontSize = 10.sp, color = buttonBackgroundColor) },
                    selected = true,
                    onClick = { /* TODO: Navigate to My Recipes */ }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorites", modifier = Modifier.size(24.dp), tint = buttonBackgroundColor) },
                    label = { Text("Favorites", fontSize = 10.sp, color = buttonBackgroundColor) },
                    selected = false,
                    onClick = { /* TODO: Navigate to Favorites */ }
                )
            }
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
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun MyRecipeCard(title: String, category: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
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
    MyRecipesScreen()
}