package com.example.hapag.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hapag.model.Recipe

class FilterViewModel: ViewModel() {

    internal val categories = listOf("All", "Breakfast", "Merienda", "Lunch", "Dinner")

    private val _selectedCategory = mutableStateOf<String?>("All")
    val selectedCategory: State<String?> = _selectedCategory

    private val _selectedCategory2 = mutableStateOf<String?>("All")
    val selectedCategory2: State<String?> = _selectedCategory2

    fun selectCategory(category: String) {
        _selectedCategory.value = if (_selectedCategory.value == category) {
            null
        } else {
            category
        }
    }

    fun selectCategory2(category: String) {
        _selectedCategory2.value = if (_selectedCategory2.value == category) {
            null
        } else {
            category
        }
    }



    fun filterRecipes(recipes: List<Recipe>): List<Recipe> {
        return when {
            // Case 1: Both filters set to "All or Null"
            selectedCategory.value == "All"  && selectedCategory2.value == "All"
                    ||  selectedCategory.value == null  && selectedCategory2.value == null
                    ||  selectedCategory.value == "All" && selectedCategory2.value == null
                    ||  selectedCategory.value == null && selectedCategory2.value == "All" -> recipes

            // Case 2: Only category2 is filtering
            selectedCategory.value == "All" || selectedCategory.value == null ->
                recipes.filter { it.category.contains(selectedCategory2.value) }

            // Case 3: Only category1 is filtering
            selectedCategory2.value == "All" || selectedCategory2.value == null ->
                recipes.filter { it.category.contains(selectedCategory.value) }

            // Case 4: Both filters are active (AND condition)
            else -> recipes.filter { recipe ->
                recipe.category.contains(selectedCategory.value) &&
                        recipe.category.contains(selectedCategory2.value)
            }
        }
    }
}
