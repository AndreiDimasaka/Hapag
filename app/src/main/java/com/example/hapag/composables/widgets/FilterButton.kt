package com.example.hapag.composables.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.RecipeViewModel

@Composable
fun FilterButton(
    modifier: Modifier = Modifier,
    viewModel : RecipeViewModel
) {
    val categories = listOf("All", "Lunch", "Merienda", "Dinner")
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(categories){ category ->
            Button(
                modifier = modifier,
                onClick = {viewModel.setCategoryFilter(category)},
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedCategory == category) AppTheme.colorScheme.primary else AppTheme.colorScheme.tertiary,
                    contentColor = if (selectedCategory == category) AppTheme.colorScheme.background else AppTheme.colorScheme.onBackground
                ),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = category,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Composable
fun FilterRow(
    modifier: Modifier = Modifier,
    viewModel : RecipeViewModel
) {
    val taste = listOf("All", "Sweet", "Savory")
    val selectedTaste by viewModel.selectedTaste.collectAsState()

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        taste.forEach { taste ->
            Text(
                text = taste,
                color = animateColorAsState(
                    targetValue =
                        if (selectedTaste == taste)
                        AppTheme.colorScheme.secondary
                    else
                        AppTheme.colorScheme.onBackground,
                    animationSpec = tween(300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { viewModel.setTasteFilter(taste)},
                textAlign = TextAlign.Center
            )
        }
    }
}
