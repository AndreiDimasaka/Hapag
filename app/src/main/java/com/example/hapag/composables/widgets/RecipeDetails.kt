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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapag.data.Recipe
import com.example.hapag.theme.AppTheme

@Composable
fun RecipeDetails(
    onAddToFavorites: () -> Unit,
    recipe: Recipe?
) {
   var categories by remember { mutableStateOf("")}
    categories = recipe?.category?.forEach { category -> "$category, " }.toString()

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
                tint = AppTheme.colorScheme.onBackground,
                modifier = Modifier.size(25.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Add to Favorites",
                style = AppTheme.typography.labelMedium.copy(fontSize = 14.sp),
                color = AppTheme.colorScheme.onBackground
            )
        }
        Divider(color = AppTheme.colorScheme.secondary, thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Description",
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        if (recipe != null) {
            Text(recipe.description, style = AppTheme.typography.bodySmall, color = AppTheme.colorScheme.onBackground)
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = AppTheme.colorScheme.secondary, thickness = 1.dp)
        Text(
            "Ingredients",
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        recipe?.ingredients?.forEach { ingredient ->
            Text(
                "- $ingredient",
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
            recipe?.instructions?.forEachIndexed { index, step ->
                Text(
                    "${index + 1}. $step",
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
    AppTheme {
        RecipeDetails(
            recipe = TODO(),
            onAddToFavorites = {}
        )
    }
}