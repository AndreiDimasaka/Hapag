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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hapag.buttonBackgroundColor
import com.example.hapag.theme.AppTheme

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
            style = AppTheme.typography.labelLarge.copy(fontSize = 26.sp),
            color = AppTheme.colorScheme.onBackground,
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
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colorScheme.onBackground
            )
            Text(
                prepTime,
                style = AppTheme.typography.labelSmall.copy(fontSize = 14.sp),
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
        Spacer(modifier = Modifier.height(8.dp))
        Text(description, style = AppTheme.typography.bodySmall, color = AppTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.height(12.dp))
        Divider(color = AppTheme.colorScheme.secondary, thickness = 1.dp)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Ingredients",
            style = AppTheme.typography.labelMedium,
            color = AppTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        ingredients.forEach { ingredient ->
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
            procedure.forEachIndexed { index, step ->
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
 recipeTitle = "Delicious Chicken Adobo",
 foodType = "Main Course",
 prepTime = "45 mins",
 description = "A classic Filipino dish made with chicken, soy sauce, vinegar, garlic, and black peppercorns.",
 ingredients = listOf(
 "1 kg chicken, cut into serving pieces",
 "1/2 cup soy sauce",
 "1/2 cup vinegar",
 "1 head garlic, crushed",
 "1 tbsp whole black peppercorns",
 "2 pcs bay leaves",
 "Cooking oil"
 ),
 procedure = listOf(
 "In a large pot, combine chicken, soy sauce, vinegar, garlic, peppercorns, and bay leaves. Marinate for at least 30 minutes.",
 "Place the pot over medium heat and bring to a boil. Reduce heat and simmer for 30-40 minutes, or until chicken is tender and sauce has thickened.",
 "Remove chicken from the pot and pan-fry in a separate pan until golden brown.",
 "Return fried chicken to the pot with the sauce. Simmer for another 5-10 minutes.",
 "Serve hot with rice."
 ),
 onAddToFavorites = {
 // Handle add to favorites action
 }
 )
 }
}
