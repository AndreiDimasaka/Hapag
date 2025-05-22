package com.example.hapag.composables.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hapag.model.Recipe
import com.example.hapag.theme.AppTheme

@Composable
fun RecipeListItem(
    recipe: Recipe,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier =
            modifier.fillMaxWidth()
                .clickable(onClick = onClick )
    ) {
        Text(
            text = recipe.title,
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colorScheme.onBackground
        )
        Text(
            text = recipe.category[0],
            style = AppTheme.typography.bodyMedium,
            color = AppTheme.colorScheme.onBackground
        )
    }
}