package com.example.hapag.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.hapag.composables.widgets.RecipeListItem
import com.example.hapag.model.Recipe
import com.example.hapag.theme.AppTheme
import com.example.hapag.viewModel.SharedViewModel


@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: SharedViewModel
) {

    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()

    SearchScreen(
        searchQuery = viewModel.searchQuery,
        searchResults = searchResults,
        onSearchQueryChange = { viewModel.onSearchQueryChange(it) },
        navController = navController,
        paddingValues = paddingValues,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchQuery: String,
    searchResults: List<Recipe>,
    onSearchQueryChange: (String) -> Unit,
    navController: NavController,
    paddingValues: PaddingValues
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(AppTheme.colorScheme.background)
    ){
        SearchBar(
            colors = SearchBarDefaults.colors(
                containerColor = AppTheme.colorScheme.tertiary,
                inputFieldColors = SearchBarDefaults.inputFieldColors(
                    focusedTextColor = AppTheme.colorScheme.onTertiary,
                    cursorColor = AppTheme.colorScheme.onTertiary,
                    disabledTextColor = AppTheme.colorScheme.tertiary,
                )
            ),
            shape = RoundedCornerShape(12.dp),
            leadingIcon = {
                Icon(
                    Icons.Filled.Search,
                    contentDescription = "search",
                    tint = AppTheme.colorScheme.onTertiary
                )
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchQueryChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            tint = AppTheme.colorScheme.onBackground,
                            contentDescription = "Clear search"
                        )
                    }
                }
            },
            modifier = Modifier,
            query = searchQuery,
            onQueryChange = onSearchQueryChange,
            onSearch = { keyboardController?.hide() },
            active = true,
            windowInsets = WindowInsets(0.dp),
            onActiveChange = { },
            placeholder = {
                Text(
                    text = "Search Recipes",
                    style = AppTheme.typography.bodyLarge,
                    color = AppTheme.colorScheme.onTertiary,
                )
            }
        )
        {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize().background(AppTheme.colorScheme.background)
            ) {
                items(
                    items = searchResults,
                    key = { it.title }) { recipe ->
                    RecipeListItem(
                        recipe = recipe,
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            navController.navigate("Recipe/${recipe.title}")
                        }
                    )

                }
            }
        }
    }
}