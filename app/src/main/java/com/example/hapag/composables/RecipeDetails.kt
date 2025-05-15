package com.example.hapag.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
<<<<<<< HEAD
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hapag.ViewModel.RecipeViewModel
import com.example.hapag.data.Recipe
import com.example.hapag.theme.AppTheme
=======
import com.example.hapag.buttonBackgroundColor
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen)


@Composable
fun RecipeDetails(
    navController: NavController,
    recipe: Recipe
) {
<<<<<<< HEAD
    val recipeViewModel = viewModel<RecipeViewModel>()
    var isFavorite by remember { mutableStateOf(recipeViewModel.isFavorite(recipe))}

        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = {
                TopReturnBar(
                    title = recipe.title,
                    arrowBack = true,
                    onNavigateBack = { navController.navigateUp() }
=======
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            recipeTitle,
            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp),
            color = buttonBackgroundColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 4.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                foodType,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = buttonBackgroundColor
            )
            Text(
                prepTime,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = buttonBackgroundColor
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .clickable { onAddToFavorites() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                Icons.Filled.FavoriteBorder,
                contentDescription = "Add to Favorites",
                tint = buttonBackgroundColor,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Add to Favorites",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                color = buttonBackgroundColor
            )
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Description",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
            color = buttonBackgroundColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(description, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp), color = buttonBackgroundColor)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.LightGray, thickness = 1.dp)
        Text(
            "Ingredients",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
            color = buttonBackgroundColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        ingredients.forEach { ingredient ->
            Text(
                "- $ingredient",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                color = buttonBackgroundColor,
                modifier = Modifier.padding(vertical = 1.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Procedure",
            style = MaterialTheme.typography.headlineSmall.copy(fontSize = 18.sp),
            color = buttonBackgroundColor
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column {
            procedure.forEachIndexed { index, step ->
                Text(
                    "${index + 1}. $step",
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                    color = buttonBackgroundColor,
                    modifier = Modifier.padding(vertical = 2.dp)
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen)
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Title
                Text(
                    text = recipe.title,
                    style = AppTheme.typography.labelLarge.copy(fontSize = 28.sp),
                    color = AppTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )

                // Metadata (Categories and Cook Time)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recipe.categories.joinToString(", "),
                        style = AppTheme.typography.labelMedium.copy(fontSize = 16.sp),
                        color = AppTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Cook Time: ${recipe.cookTime}",
                        style = AppTheme.typography.labelSmall.copy(fontSize = 14.sp),
                        color = AppTheme.colorScheme.onBackground
                    )
                }

                // Serving Size
                Text(
                    text = "Serves: ${recipe.servingSize}",
                    style = AppTheme.typography.labelSmall.copy(fontSize = 14.sp),
                    color = AppTheme.colorScheme.onBackground
                )

                // Favorite Button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            recipeViewModel.toggleFavorite(recipe)
                            isFavorite = recipeViewModel.isFavorite(recipe)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from Favorites" else "Add to Favorites",
                        tint = AppTheme.colorScheme.secondary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isFavorite) "Remove from Favorites" else "Add to Favorites",
                        style = AppTheme.typography.labelMedium.copy(fontSize = 14.sp),
                        color = AppTheme.colorScheme.onBackground
                    )
                }

                Divider(color = AppTheme.colorScheme.secondary, thickness = 1.dp)

                // Description
                Text(
                    text = "Description",
                    style = AppTheme.typography.labelMedium.copy(fontSize = 18.sp),
                    color = AppTheme.colorScheme.onBackground
                )
                Text(
                    text = recipe.description,
                    style = AppTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = AppTheme.colorScheme.onBackground
                )

                Divider(color = AppTheme.colorScheme.secondary, thickness = 1.dp)

                // Ingredients
                Text(
                    text = "Ingredients",
                    style = AppTheme.typography.labelMedium.copy(fontSize = 18.sp),
                    color = AppTheme.colorScheme.onBackground
                )
                recipe.ingredients.forEach { ingredient ->
                    Text(
                        text = "• ${ingredient.value}",
                        style = AppTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                        color = AppTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                Divider(color = AppTheme.colorScheme.secondary, thickness = 1.dp)

                // Procedure
                Text(
                    text = "Procedure",
                    style = AppTheme.typography.labelMedium.copy(fontSize = 18.sp),
                    color = AppTheme.colorScheme.onBackground
                )
                recipe.procedures.forEach { procedure ->
                    Text(
                        text = "${procedure.step}. ${procedure.description}",
                        style = AppTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                        color = AppTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
<<<<<<< HEAD

// Preview
@Composable
fun PreviewRecipeDetails() {

}
=======
}
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen)
