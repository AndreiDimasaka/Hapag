package com.example.hapag.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.hapag.theme.AppTheme


@Composable
fun RecipeCard(
    foodName: String = "Recipe Title",
    foodType: String = "Food Type",
    imageRes: Int? = null,
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
                if (imageRes != null) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = foodName,
                        modifier = Modifier.fillMaxSize()
                            .aspectRatio(1.7f),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text("No Image", textAlign = TextAlign.Center)
                }
            }
            Text(text = foodName, fontWeight = FontWeight.Bold)
            Text(text = foodType)
        }
    }

}
