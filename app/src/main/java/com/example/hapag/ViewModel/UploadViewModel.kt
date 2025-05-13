package com.example.hapag.ViewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hapag.composables.Item
import com.example.hapag.data.Recipe
import com.example.hapag.data.toggleableCategory

class UploadViewModel: ViewModel() {
    val viewmodel = MyRecipeViewModel()
    var uploadedImage by mutableStateOf<Uri?>(null)
    var title = mutableStateOf("")
    var description = mutableStateOf("")
    var servingSize = mutableStateOf("")
    var cookTime = mutableStateOf("")
    var category = mutableStateOf("")

    fun uploadRecipe(){
        for (item in categoryCheckBox) {
            if (item.isChecked) {
                category.value = category.value + item.text + ", "
            }
        }
        val uploadrecipe = Recipe(uploadedImage?: Uri.EMPTY,title.value, description.value, servingSize.value, cookTime.value, category.value, ingredientList.map {it.text}, prodecureList.map { it.text })
        viewmodel.addMyRecipe(uploadrecipe)
    }


    val categoryCheckBox = mutableStateListOf<toggleableCategory>(
        toggleableCategory(false, "Breakfast"),
        toggleableCategory(false, "Lunch"),
        toggleableCategory(false, "Dinner"),
        toggleableCategory(false, "Merienda  "),
        toggleableCategory(false, "Sweet"),
        toggleableCategory(false, "Savory")
    )

    //Ingredient
    var overlayIngredientList by mutableStateOf(false)
        internal set

    val ingredientList = mutableStateListOf<Item>()
    init {
        ingredientList.addAll(listOf())
    }

    fun openIngredientList() {
        overlayIngredientList = true
    }

    fun closeIngredientList() {
        overlayIngredientList = false
    }
    fun addIngredient() {
        val nextId = (ingredientList.maxOfOrNull { it.id } ?: 0) + 1
        ingredientList.add(Item(nextId, ""))
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

    // Procedure
    var overlayProcedureList by mutableStateOf(false)
        internal set


    val prodecureList = mutableStateListOf<Item>()
    init {
        prodecureList.addAll(listOf())
    }

    fun openProcedureList() {
        overlayProcedureList = true
    }

    fun closeProcedureList() {
        overlayProcedureList = false
    }

    fun addProcedure() {
        val nextId = (prodecureList.maxOfOrNull { it.id } ?: 0) + 1
        prodecureList.add(Item(nextId, ""))
    }

    fun removeProcedure(id: Int) {
        prodecureList.removeAll { it.id == id }
    }

    fun updateProcedure(id: Int, newText: String) {
        val index = prodecureList.indexOfFirst { it.id == id }
        if (index != -1) {
            prodecureList[index] = prodecureList[index].copy(text = newText)
        }
    }

    fun reorderProcedure(fromIndex: Int, toIndex: Int) {
        val item = prodecureList.removeAt(fromIndex)
        prodecureList.add(toIndex, item)
    }
}