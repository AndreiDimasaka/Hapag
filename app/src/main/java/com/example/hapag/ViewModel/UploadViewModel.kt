package com.example.hapag.ViewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
<<<<<<< HEAD
<<<<<<< HEAD
=======
import androidx.compose.runtime.setValue
>>>>>>> parent of e5dfdad (adding navcontroller)
import androidx.lifecycle.ViewModel
import com.example.hapag.composables.Item
import com.example.hapag.data.Recipe
import com.example.hapag.data.toggleableCategory

<<<<<<< HEAD
=======
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hapag.composables.Item

class UploadViewModel: ViewModel() {

    var uploadedImage by mutableStateOf<Uri?>(null)
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen)
=======
class UploadViewModel: ViewModel() {
    val viewmodel = MyRecipeViewModel()
    var uploadedImage by mutableStateOf<Uri?>(null)
>>>>>>> parent of e5dfdad (adding navcontroller)
    var title = mutableStateOf("")
    var description = mutableStateOf("")
    var servingSize = mutableStateOf("")
    var cookTime = mutableStateOf("")
<<<<<<< HEAD
<<<<<<< HEAD
        private set
=======
    var category = mutableStateOf("")
>>>>>>> parent of e5dfdad (adding navcontroller)

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
=======



>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen)

    //Ingredient
    var overlayIngredientList by mutableStateOf(false)
        internal set

<<<<<<< HEAD
<<<<<<< HEAD
    // Overlay states
    var overlayIngredientList = mutableStateOf(false)
        private set

    var overlayProcedureList = mutableStateOf(false)
        private set

    // Update functions for text fields
    fun updateTitle(newTitle: String) {
        title.value = newTitle
=======
    val ingredientList = mutableStateListOf<Item>()
    init {
        ingredientList.addAll(listOf(
            Item(1, ""),
            Item(2, "")
        ))
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen)
=======
    val ingredientList = mutableStateListOf<Item>()
    init {
        ingredientList.addAll(listOf())
>>>>>>> parent of e5dfdad (adding navcontroller)
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
<<<<<<< HEAD
<<<<<<< HEAD
        if (fromIndex in ingredientsList.indices && toIndex in ingredientsList.indices) {
            val item = ingredientsList[fromIndex]
            ingredientsList.removeAt(fromIndex)
            ingredientsList.add(toIndex, item)
        }
=======
=======
>>>>>>> parent of e5dfdad (adding navcontroller)
        val item = ingredientList.removeAt(fromIndex)
        ingredientList.add(toIndex, item)
    }

    // Procedure
    var overlayProcedureList by mutableStateOf(false)
        internal set


    val prodecureList = mutableStateListOf<Item>()
    init {
<<<<<<< HEAD
        prodecureList.addAll(listOf(
            Item(1, ""),
            Item(2, "")
        ))
=======
        prodecureList.addAll(listOf())
>>>>>>> parent of e5dfdad (adding navcontroller)
    }

    fun openProcedureList() {
        overlayProcedureList = true
    }

    fun closeProcedureList() {
        overlayProcedureList = false
<<<<<<< HEAD
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen)
=======
>>>>>>> parent of e5dfdad (adding navcontroller)
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