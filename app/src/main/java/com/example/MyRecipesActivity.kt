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
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.compose.material3.Icon
import com.example.hapag.ui.BottomNavigationBar // Import

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
                title = {
                    Text(
                        "My Recipes",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = buttonBackgroundColor
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Navigate back */ }) {
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