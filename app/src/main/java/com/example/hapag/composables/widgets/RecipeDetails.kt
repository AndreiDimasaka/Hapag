package com.example.hapag.composables.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.RecipeViewModel

@Composable
fun RecipeDetails(
    recipeId: Long?,
    viewModel: RecipeViewModel,
    navController: NavController
) {
    val recipe by viewModel.recipeState.collectAsState()
    val ingredients by viewModel.ingredientsState.collectAsState()
    val instructions by viewModel.instructionsState.collectAsState()
    val listCategories by viewModel.categoriesState.collectAsState()

    LaunchedEffect(recipeId) {
        viewModel.loadRecipe(recipeId)
    }

    val categories = listCategories.joinToString { it.name }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            recipe?.title ?:  "" ,
            style = AppTheme.typography.labelLarge.copy(fontSize = 26.sp),
            color = AppTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = categories,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colorScheme.onBackground
            )
            Text(
                recipe?.cookTime ?: "",
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
                if(recipe?.isFavorite == true) {Icons.Filled.Favorite } else Icons.Filled.FavoriteBorder,
                contentDescription = "Add to Favorites",
                tint = AppTheme.colorScheme.secondary,
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        recipe?.let {
                            viewModel.toggleFavorite(it.id, !it.isFavorite)
                        }
                    }
        )
        Divider(color = AppTheme.colorScheme.secondary, thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Description",
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        recipe?.let { Text(it.description, style = AppTheme.typography.bodySmall, color = AppTheme.colorScheme.onBackground) }
        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = AppTheme.colorScheme.secondary, thickness = 1.dp)
        Text(
            "Ingredients",
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        ingredients.forEach { ingredient ->
            Text(
                text = ingredient.name,
                style = AppTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                color = AppTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Procedure",
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Column {
            instructions.forEachIndexed { index, step ->
                Text(
                    text = "${index + 1}. ${step.instructionText}" ,
                    style = AppTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = AppTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


// Preview
@Composable
@Preview(showBackground = true)
fun PreviewRecipeDetails() {

}