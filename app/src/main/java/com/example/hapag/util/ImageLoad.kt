package com.example.hapag.util

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.hapag.theme.AppTheme

@Composable
fun ImageLoader(
    imagePath: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val context = LocalContext.current

    val imageRequest = remember(imagePath) {
        ImageRequest.Builder(context).apply {
            if (imagePath != null) {
                if (imagePath.startsWith("file:///android_asset/") || imagePath.startsWith("content://") || imagePath.startsWith("file://")) {
                    data(Uri.parse(imagePath))
                } else if (imagePath.contains("/")) {
                    data(Uri.parse("file:///android_asset/$imagePath"))
                } else {
                    Log.w("ImageLoader", "Unrecognized image path format: '$imagePath'. Treating as null.")
                    data(null) // Invalid format, will show placeholder
                }
            } else {
                data(null) // No image path provided, will show placeholder
            }

            crossfade(true)
        }.build()
    }

    val painter = rememberAsyncImagePainter(imageRequest)

    if (imagePath != null && imageRequest.data != null) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    } else {
        Box(
            modifier = modifier.background(AppTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Image",
                color = AppTheme.colorScheme.onBackground,
            )
        }
    }
}