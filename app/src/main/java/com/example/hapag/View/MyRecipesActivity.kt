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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hapag.ViewModel.MyRecipeViewModel
import com.example.hapag.composables.MyRecipeCard
import com.example.hapag.composables.TopReturnBar
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar

class MyRecipesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MyRecipesScreen(onBack = { finish() }) // Pass the finish() lambda
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRecipesScreen(onBack: () -> Unit) { // Add the onBack parameter
    val viewModel = viewModel<MyRecipeViewModel>()
    val context = LocalContext.current
    Scaffold(
        topBar = { TopReturnBar(title = "My Recipes", arrowBack = false,) },
        bottomBar = {
            BottomNavigationBar(onItemSelected = { index ->
                // TODO: Implement navigation based on the selected index
                println("My Recipes: Bottom navigation item selected at index: $index")
            }, selectedIndex = 2) // My Recipes is at index 2
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
            viewModel.myRecipeList.forEach{
                MyRecipeCard(
                    title = it.title,
                    category = it.category,
                    modifier = Modifier.fillMaxWidth(),
                    onRecipeClick = {
                        val intent = Intent(context, RecipeActivity::class.java).apply {
                            putExtra("recipe", it.title)
                        }
                        context.startActivity(intent) }
                )
        }
    }
}
    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyRecipesScreenPreview() {
    MyRecipesScreen(onBack = {})
}