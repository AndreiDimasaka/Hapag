package com.example.hapag.composables.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.hapag.dummyinputs.BlankRecipeScreen
import com.example.hapag.dummyinputs.HaloHaloRecipeScreen
import com.example.hapag.dummyinputs.LecheFlanRecipeScreen
import com.example.hapag.composables.widgets.RecipeDetails
import com.example.hapag.data.ImageData
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.sharedViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.hapag.data.Recipe


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeScreen(
    navController: NavController,
    sharedViewModel: sharedViewModel,
    recipe: Recipe?
) {
    var showFullScreenImage by remember { mutableStateOf(false) }

    AppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick =  {navController.navigateUp()}) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = AppTheme.colorScheme.onSecondary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = AppTheme.colorScheme.secondary)
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppTheme.colorScheme.background)
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                (recipe?.image as? ImageData.DrawableRes)?.let {
                    Image(
                        painter = painterResource(id = it.resId),
                        contentDescription = recipe!!.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clickable { showFullScreenImage = true },
                        contentScale = ContentScale.Crop
                    )
                }
                (recipe?.image as? ImageData.UriVal)?.let {
                    AsyncImage(
                        model = it.uri,
                        contentDescription = recipe!!.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clickable{showFullScreenImage = true },
                        contentScale = ContentScale.Crop
                    )
                }
                    AnimatedVisibility(
                        visible = showFullScreenImage,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Dialog(onDismissRequest = { showFullScreenImage = false }) {
                            Box(
                                Modifier.fillMaxSize()
                            ) {
                                if (recipe?.image is ImageData.DrawableRes) {
                                    Image(
                                        painter = painterResource(
                                            id = (recipe!!.image as ImageData.DrawableRes).resId
                                        ),
                                        contentDescription = recipe!!.title,
                                        modifier = Modifier.fillMaxSize()
                                            .clickable { showFullScreenImage = false },
                                        contentScale = ContentScale.Fit
                                    )
                                    IconButton(
                                        onClick = { showFullScreenImage = false },
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .padding(8.dp)
                                    ) {
                                        Icon(
                                            Icons.Filled.Close,
                                            contentDescription = "Close",
                                            tint = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                RecipeDetails(
                    onAddToFavorites = {},
                    recipe = recipe
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHaloHaloRecipeScreen() {
    HaloHaloRecipeScreen(onNavigateBack = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLecheFlanRecipeScreen() {
    LecheFlanRecipeScreen(onNavigateBack = {})
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewBlankRecipeScreen() {
    BlankRecipeScreen(onNavigateBack = {})
}