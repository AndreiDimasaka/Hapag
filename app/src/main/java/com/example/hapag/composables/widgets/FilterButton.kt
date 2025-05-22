package com.example.hapag.composables.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.FilterViewModel

@Composable
fun FilterButton(
    text: String,
    modifier: Modifier = Modifier,
    filterViewModel : FilterViewModel
) {
    val isSelected = filterViewModel.selectedCategory.value == text

    Button(
        modifier = modifier,
        onClick = {filterViewModel.selectCategory(text)},
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) AppTheme.colorScheme.primary else AppTheme.colorScheme.tertiary,
            contentColor = if (isSelected) AppTheme.colorScheme.background else AppTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Composable
fun FilterRow(
    modifier: Modifier = Modifier,
    filterViewModel: FilterViewModel
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        listOf("All", "Sweet", "Savory").forEach { category ->
            Text(
                text = category,
                color = animateColorAsState(
                    targetValue =
                        if (filterViewModel.selectedCategory2.value == category)
                        AppTheme.colorScheme.secondary
                    else
                        AppTheme.colorScheme.onBackground,
                    animationSpec = tween(300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { filterViewModel.selectCategory2(category) },
                textAlign = TextAlign.Center
            )
        }
    }
}
