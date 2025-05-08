package com.example.hapag.composables

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hapag.R
import com.example.hapag.buttonTextColor
import com.example.hapag.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FigmaDashboardLayout(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    onRecipeClick: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("All") }
    var selectedSweetSavoryTab by remember { mutableStateOf("All") }
    val categories = listOf("All", "Breakfast", "Lunch", "Merienda", "Dinner")

    val sweetFilipinoFoods = remember {
        listOf(
            Triple("Halo-Halo", "Merienda", R.drawable.halohalo),
            Triple("Leche Flan", "Dessert", R.drawable.lecheflan),
            Triple("Ube Halaya", "Dessert", R.drawable.ubehalaya),
            Triple("Bibingka", "Breakfast", R.drawable.bibingka),
            Triple("Puto Bumbong", "Breakfast", R.drawable.bumbong),
            Triple("Kutsinta", "Merienda", R.drawable.kutsinta),
            Triple("Suman", "Breakfast", R.drawable.suman),
            Triple("Buko Pandan", "Dessert", R.drawable.bukopandan),
            Triple("Turon", "Merienda", R.drawable.turon),
            Triple("Banana Cue", "Merienda", R.drawable.bananacue)
        )
    }

    val savoryFilipinoFoods = remember {
        listOf(
            Triple("Pork Adobo", "Lunch", R.drawable.adobo),
            Triple("Pork Sinigang", "Dinner", R.drawable.sinigang),
            Triple("Tortang Talong", "Dinner", R.drawable.torta),
            Triple("Kare-Kare", "Lunch", R.drawable.karekare),
            Triple("Pinakbet", "Lunch", R.drawable.pinakbet),
            Triple("Laing", "Dinner", R.drawable.laing),
            Triple("Pancit Bihon", "Merienda", R.drawable.pancit),
            Triple("Lumpia", "Merienda", R.drawable.lumpia),
            Triple("Sisig", "Lunch", R.drawable.sisig),
            Triple("Bulalo", "Dinner", R.drawable.bulalo)
        )
    }

    val filteredByCategory = remember(selectedCategory) {
        if (selectedCategory == "All") {
            sweetFilipinoFoods + savoryFilipinoFoods
        } else {
            (sweetFilipinoFoods + savoryFilipinoFoods).filter { it.second.equals(selectedCategory, ignoreCase = true) }
        }
    }

    val displayFoods = remember(selectedSweetSavoryTab, filteredByCategory) {
        when (selectedSweetSavoryTab) {
            "All" -> filteredByCategory
            "Sweet" -> filteredByCategory.filter { it.first in sweetFilipinoFoods.map { it.first } }
            "Savory" -> filteredByCategory.filter { it.first in savoryFilipinoFoods.map { it.first } }
            else -> filteredByCategory
        }
    }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columnCount = if (isLandscape) 4 else 2

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchBar(
                colors = androidx.compose.material3.SearchBarDefaults.colors(
                    containerColor = AppTheme.colorScheme.tertiary,
                    dividerColor = AppTheme.colorScheme.tertiary
                ),
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "search", tint = AppTheme.colorScheme.onTertiary ) },
                modifier = Modifier.weight(1f),
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = { isActive = false },
                active = isActive,
                onActiveChange = { isActive = it },
                placeholder = { Text(
                    text = "Search",
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colorScheme.onTertiary) }
            ) {}
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Category",
            color = AppTheme.colorScheme.onBackground,
            style = AppTheme.typography.labelLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp)) // Added space after "Category"
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp), // Made categories closer
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(categories, key= {it}) { category ->
                FilterButton(
                    text = category,
                    isSelected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    backgroundColor = AppTheme.colorScheme.tertiary,
                    textColor = AppTheme.colorScheme.onTertiary,
                    modifier = Modifier.widthIn(min = if (isLandscape) 220.dp else Dp.Unspecified)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Divider(color = AppTheme.colorScheme.secondary.copy(0.7f), thickness = 3.dp)
        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Text(
                text = "All",
                color = animateColorAsState(
                    targetValue = if (selectedSweetSavoryTab == "All") AppTheme.colorScheme.secondary else AppTheme.colorScheme.onBackground,
                    animationSpec = tween(durationMillis = 300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedSweetSavoryTab = "All" },
                textAlign = TextAlign.Center
            )
            Text(
                text = "Sweet",
                color = animateColorAsState(
                    targetValue = if (selectedSweetSavoryTab == "Sweet") AppTheme.colorScheme.secondary else AppTheme.colorScheme.onBackground,
                    animationSpec = tween(durationMillis = 300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedSweetSavoryTab = "Sweet" },
                textAlign = TextAlign.Center
            )
            Text(
                text = "Savory",
                color = animateColorAsState(
                    targetValue = if (selectedSweetSavoryTab == "Savory")AppTheme.colorScheme.secondary else AppTheme.colorScheme.onBackground,
                    animationSpec = tween(durationMillis = 300)
                ).value,
                modifier = Modifier
                    .weight(1f)
                    .clickable { selectedSweetSavoryTab = "Savory" },
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(color = AppTheme.colorScheme.secondary.copy(0.7f), thickness = 3.dp)
        Spacer(modifier = Modifier.height(20.dp))

        Column(horizontalAlignment = Alignment.Start) {
            for (rowIndex in 0 until (displayFoods.size + columnCount - 1) / columnCount) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    for (i in 0 until columnCount) {
                        val foodIndex = rowIndex * columnCount + i
                        if (foodIndex < displayFoods.size) {
                            val (foodName, foodType, imageRes) = displayFoods[foodIndex]
                            RecipeCard(
                                foodName = foodName,
                                foodType = foodType,
                                imageRes = imageRes,
                                onClick = { onRecipeClick(foodName) },
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                        if (i < columnCount - 1 && foodIndex < displayFoods.size - 1) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp)
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
        FigmaDashboardLayout(buttonBackgroundColor = Color.LightGray, onRecipeClick = {})
    }
}
}


