package com.example.hapag.composables.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.hapag.theme.AppTheme

@Composable
fun MyRecipeCard(
    title: String,
    category: List<String>,
    modifier: Modifier = Modifier,
    onRecipeClick: () -> Unit,
    onRemoveClick: () -> Unit,
    icon : @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
            .background(AppTheme.colorScheme.tertiary)
            .clickable(onClick = onRecipeClick) // Make the entire row clickable
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(title, style = AppTheme.typography.labelLarge, color = AppTheme.colorScheme.onTertiary)
            if (category.isNotEmpty()) {
                Text(
                    "Category: ${category[0]}",
                    style = AppTheme.typography.labelSmall,
                    color = AppTheme.colorScheme.onTertiary
                )
            }
        }
        IconButton(onClick = onRemoveClick) {
            icon()
        }
    }
}