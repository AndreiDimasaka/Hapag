package com.example.hapag.composables.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hapag.data.ImageData
import com.example.hapag.theme.AppTheme
import com.example.hapag.data.Recipe


@Composable
fun RecipeCard(
    recipe: Recipe,
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
                if (recipe.image is ImageData.DrawableRes) {
                    Image(
                        painter = painterResource(id = (recipe.image as ImageData.DrawableRes).resId),
                        contentDescription = recipe.title,
                        modifier = Modifier.fillMaxSize()
                            .aspectRatio(1.7f),
                        contentScale = ContentScale.Crop
                    )
                    if (recipe.image is ImageData.UriVal) {
                        AsyncImage(
                            model = (recipe.image as ImageData.UriVal).uri,
                            contentDescription = recipe.title,
                            modifier = Modifier.fillMaxSize()
                                .aspectRatio(1.7f),
                            contentScale = ContentScale.Crop
                        )
                    }
                } else {
                    Text("No Image", textAlign = TextAlign.Center)
                }
            }
            Text(text = recipe.title, fontWeight = FontWeight.Bold)
            Text(text = recipe.category)
        }
    }

}
