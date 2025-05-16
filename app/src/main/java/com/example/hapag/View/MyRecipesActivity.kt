package com.example.hapag

import android.content.Intent
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
=======
=======
>>>>>>> parent of e5dfdad (adding navcontroller):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
<<<<<<< HEAD
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt
=======
>>>>>>> parent of e5dfdad (adding navcontroller):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
=======
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
>>>>>>> parent of cdb655a (Applied app theme to all activities and composables, building view model)
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.platform.LocalContext
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hapag.RecipeActivity
import com.example.hapag.ViewModel.RecipeViewModel
=======
=======
>>>>>>> parent of cdb655a (Applied app theme to all activities and composables, building view model)
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
<<<<<<< HEAD
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt
=======
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hapag.ViewModel.MyRecipeViewModel
>>>>>>> parent of e5dfdad (adding navcontroller):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt
import com.example.hapag.composables.MyRecipeCard
import com.example.hapag.composables.TopReturnBar
import com.example.hapag.theme.AppTheme
import com.example.hapag.ui.BottomNavigationBar
=======
import androidx.compose.material3.Icon
import com.example.hapag.ui.BottomNavigationBar // Import
import androidx.compose.ui.platform.LocalContext
>>>>>>> parent of cdb655a (Applied app theme to all activities and composables, building view model)

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
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
fun MyRecipesScreen(onBack: () -> Unit,
                    navController: NavController,
                    recipeViewModel: RecipeViewModel
) { // Add the onBack parameter
    val recipeViewModel = recipeViewModel
=======
fun MyRecipesScreen(onBack: () -> Unit) { // Add the onBack parameter
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt
=======
fun MyRecipesScreen(onBack: () -> Unit) { // Add the onBack parameter
    val viewModel = viewModel<MyRecipeViewModel>()
>>>>>>> parent of e5dfdad (adding navcontroller):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt
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
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
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
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
=======
        }
    }
}
=======
}
    }
>>>>>>> parent of e5dfdad (adding navcontroller):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt

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
<<<<<<< HEAD:app/src/main/java/com/example/hapag/composables/screen/ScreenMyRecipe.kt
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt
=======
>>>>>>> parent of e5dfdad (adding navcontroller):app/src/main/java/com/example/hapag/View/MyRecipesActivity.kt
}