package com.example.hapag.ViewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapag.data.Ingredient
import com.example.hapag.data.Procedure
import com.example.hapag.data.Recipe
import com.example.hapag.data.toggleableCategory
import kotlinx.coroutines.launch

class UploadViewModel : ViewModel() {

    var title = mutableStateOf("")
        private set

    var description = mutableStateOf("")
        private set

    var servingSize = mutableStateOf("")
        private set

    var cookTime = mutableStateOf("")
        private set

    // Category checkbox list
    val categoryCheckBox = mutableStateListOf(
        toggleableCategory("Breakfast", false),
        toggleableCategory("Lunch", false),
        toggleableCategory("Dinner", false),
        toggleableCategory("Snack", false),
        toggleableCategory("Dessert", false)
    )

    // Ingredient and Procedure lists
    val ingredientsList = mutableStateListOf<Ingredient>()
    val proceduresList = mutableStateListOf<Procedure>()

    // Overlay states
    var overlayIngredientList = mutableStateOf(false)
        private set

    var overlayProcedureList = mutableStateOf(false)
        private set

    // Update functions for text fields
    fun updateTitle(newTitle: String) {
        title.value = newTitle
    }

    fun updateDescription(newDescription: String) {
        description.value = newDescription
    }

    fun updateServingSize(newServingSize: String) {
        servingSize.value = newServingSize
    }

    fun updateCookTime(newCookTime: String) {
        cookTime.value = newCookTime
    }

    // Overlay control functions
    fun openIngredientList() {
        overlayIngredientList.value = true
    }

    fun closeIngredientList() {
        overlayIngredientList.value = false
    }

    fun openProcedureList() {
        overlayProcedureList.value = true
    }

    fun closeProcedureList() {
        overlayProcedureList.value = false
    }

    // Functions to manage ingredients and procedures
    fun addIngredient(ingredient: Ingredient) {
        ingredientsList.add(ingredient)
    }

    fun removeIngredient(index: Int) {
        if (index in ingredientsList.indices) {
            ingredientsList.removeAt(index)
        }
    }

    fun updateIngredient(index: Int, ingredient: Ingredient) {
        if (index in ingredientsList.indices) {
            ingredientsList[index] = ingredient
        }
    }

    fun reorderIngredients(fromIndex: Int, toIndex: Int) {
        if (fromIndex in ingredientsList.indices && toIndex in ingredientsList.indices) {
            val item = ingredientsList[fromIndex]
            ingredientsList.removeAt(fromIndex)
            ingredientsList.add(toIndex, item)
        }
    }

    fun addProcedure() {
        val newStep = proceduresList.size + 1
        proceduresList.add(Procedure(step = newStep, description = ""))
    }

    fun removeProcedure(index: Int) {
        if (index in proceduresList.indices) {
            proceduresList.removeAt(index)
        }
    }

    fun updateProcedure(index: Int, text: String) {
        if (index in proceduresList.indices) {
            proceduresList[index] = proceduresList[index].copy(description = text)
        }
    }

    fun reorderProcedure(fromIndex: Int, toIndex: Int) {
        if (fromIndex in proceduresList.indices && toIndex in proceduresList.indices) {
            val item = proceduresList[fromIndex]
            proceduresList.removeAt(fromIndex)
            proceduresList.add(toIndex, item)
        }
    }

    fun uploadRecipe(onSuccess: (Recipe) -> Unit) {
        viewModelScope.launch {
            // Validate inputs
            if (title.value.isBlank()) {
                // Handle empty title error
                return@launch
            }
            if (ingredientsList.isEmpty()) {
                // Handle empty ingredients error
                return@launch
            }
            if (proceduresList.isEmpty()) {
                // Handle empty procedures error
                return@launch
            }

            // Create recipe object
            val recipe = Recipe(
                title = title.value,
                description = description.value,
                servingSize = servingSize.value,
                cookTime = cookTime.value,
                categories = categoryCheckBox.filter { it.isChecked }.map { it.text },
                ingredients = ingredientsList.toList(),
                procedures = proceduresList.toList()
            )

            // TODO: Implement actual upload logic (e.g., API call, database save)
            // For now, just clear the form and call onSuccess
            clearForm()
            onSuccess(recipe)
        }
    }

    private fun clearForm() {
        title.value = ""
        description.value = ""
        servingSize.value = ""
        cookTime.value = ""
        categoryCheckBox.forEachIndexed { index, _ ->
            categoryCheckBox[index] = categoryCheckBox[index].copy(isChecked = false)
        }
        ingredientsList.clear()
        proceduresList.clear()
    }
}