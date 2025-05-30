package com.example.hapag.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hapag.composables.widgets.RecipeListItem
import com.example.hapag.model.data.Recipe
import com.example.hapag.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    searchQuery: String,
    searchResults: List<Recipe>,
    onSearchQueryChange: (String) -> Unit,
    navController: NavController,
) {
    var searching by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

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
            active = searching,
            windowInsets = WindowInsets(0.dp),
            onActiveChange = { searching = it },
            placeholder = {
                Text(
                    text = "Search Recipes",
                    style = AppTheme.typography.bodyLarge,
                    color = AppTheme.colorScheme.onTertiary,
                )
            }
        )
        {
            if (searching)
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize().background(AppTheme.colorScheme.background)
            ) {
                items(
                    items = searchResults,
                    key = { it.id ?: 0 }) { recipe ->
                    RecipeListItem(
                        recipe = recipe,
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                            navController.navigate("Recipe/${recipe.id}")
                        }
                    )

                }
            }
        }
    }