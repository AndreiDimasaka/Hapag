package com.example.hapag.composables.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.FilterViewModel
import com.example.hapag.viewModel.SharedViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FigmaDashboardLayout(
    modifier: Modifier = Modifier,
    navController: NavController,
    sharedViewModel: SharedViewModel
) {
    val filterViewModel = viewModel<FilterViewModel>()
    val recipeList by sharedViewModel.recipeList.collectAsState()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    val filteredRecipes = filterViewModel.filterRecipes(recipeList)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 10.dp),
        horizontalAlignment = Alignment.Start
    ) {


        IconButton(
            onClick = { navController.navigate("Search") },
            modifier = Modifier.align(Alignment.End),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = AppTheme.colorScheme.secondary,
                contentColor = AppTheme.colorScheme.onSecondary
            )
        ) {
            Icon(
                Icons.Filled.Search,
                contentDescription = "Search",
                modifier = Modifier.size(32.dp)
            )
        }

            Text(
                text = "Category",
                color = AppTheme.colorScheme.onBackground,
                style = AppTheme.typography.labelLarge,

            )

            Spacer(modifier = Modifier.height(8.dp)) // Added space after "Category"
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Made categories closer
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(
                    filterViewModel.categories,
                    key = { it }) { category ->
                    FilterButton(
                        text = category,
                        modifier = Modifier.widthIn(min = if (isLandscape) 220.dp else Dp.Unspecified),
                        filterViewModel = filterViewModel
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = AppTheme.colorScheme.secondary.copy(0.7f), thickness = 3.dp)
            Spacer(modifier = Modifier.height(10.dp))
            FilterRow(
                modifier = Modifier.fillMaxWidth(),
                filterViewModel = filterViewModel
            )
            Spacer(modifier = Modifier.height(10.dp))
            Divider(color = AppTheme.colorScheme.secondary.copy(0.7f), thickness = 3.dp)

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    filteredRecipes,
                    key = { it.title }
                ) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            navController.navigate("Recipe/${recipe.title}")
                        }
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun FigmaDashboardLayoutPreview() {
        AppTheme {
            Surface(
                color = AppTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize()
            ) {
            }
        }
    }



