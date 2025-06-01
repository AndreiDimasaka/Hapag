package com.example.hapag.composables.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.hapag.model.data.RecipeWithCategories
import com.example.hapag.theme.AppTheme
import com.example.hapag.util.ImageLoader


@Composable
fun RecipeCard(
    recipeWithCategories: RecipeWithCategories,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {


    Card(
        modifier = modifier.padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colorScheme.tertiary,
            contentColor = AppTheme.colorScheme.onTertiary
        )
    ){
        Column(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
                contentAlignment = Alignment.Center
            ) {
                ImageLoader(
                    imagePath = recipeWithCategories.recipe.image,
                    contentDescription = recipeWithCategories.recipe.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.7f)
                )
            }
            Text(text = recipeWithCategories.recipe.title, fontWeight = FontWeight.Bold)
            if (recipeWithCategories.categories.isNotEmpty()) {
                Text(text = recipeWithCategories.categories[0].name)
            }
            }
        }
    }

