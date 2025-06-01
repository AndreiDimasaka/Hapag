package com.example.hapag.composables.widgets

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.hapag.R
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.RecipeViewModel

@Composable
fun ImageSelect(
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<RecipeViewModel>()

    val singleImagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let { viewModel.setImageUri(it) }
        }
    )
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        viewModel.uploadedImage?.let { imageUri ->
            AsyncImage(
                model = imageUri,
                contentDescription = "Selected Recipe Photo",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            FloatingActionButton(
                onClick = { viewModel.removeImageUri() },
                modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp).size(30.dp),
                containerColor = AppTheme.colorScheme.background,
                contentColor = AppTheme.colorScheme.onBackground,
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                painter = painterResource(R.drawable.icons_trash),
                contentDescription = "Remove Image",
                    modifier = Modifier.padding(3.dp)
                )}
        } ?: run {
            OutlinedButton(
                onClick = {
                    singleImagePicker.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = AppTheme.colorScheme.onSecondary
                ),
                border = null
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_camera),
                    contentDescription = "Add Image"
                )
                Text(
                    text = "ADD IMAGE",
                    style = AppTheme.typography.labelLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
