package com.example.hapag.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hapag.composables.Item

class IngredientILViewModel: ViewModel() {
    var nextId by mutableStateOf(0)

    var overlayIngredientList by mutableStateOf(false)
        internal set


    val ingredientList = mutableStateListOf<Item>()
    init {
        ingredientList.addAll(listOf(
            Item(1, "Ingredient 1"),
            Item(2, "Ingredient 2")
        ))
    }

    fun openIngredientList() {
        overlayIngredientList = true
    }

    fun closeIngredientList() {
        overlayIngredientList = false
    }
    fun addIngredient() {
        ingredientList.add(Item(nextId++, ""))
    }

    fun removeIngredient(id: Int) {
        ingredientList.removeAll { it.id == id }
    }

    fun updateIngredient(id: Int, newText: String) {
        val index = ingredientList.indexOfFirst { it.id == id }
        if (index != -1) {
            ingredientList[index] = ingredientList[index].copy(text = newText)
        }
    }

    fun reorderIngredients(fromIndex: Int, toIndex: Int) {
        val item = ingredientList.removeAt(fromIndex)
        ingredientList.add(toIndex, item)
    }
}
