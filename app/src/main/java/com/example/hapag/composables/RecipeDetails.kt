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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapag.buttonBackgroundColor

@Composable
fun RecipeDetails(
    recipeTitle: String,
    foodType: String,
    prepTime: String,
    description: String,
    ingredients: List<String>,
    procedure: List<String>,
    onAddToFavorites: () -> Unit
) {
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
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}