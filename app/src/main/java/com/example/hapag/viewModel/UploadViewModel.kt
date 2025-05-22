package com.example.hapag.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hapag.model.ImageData
import com.example.hapag.model.Item
import com.example.hapag.model.Recipe
import com.example.hapag.model.toggleableCategory

class UploadViewModel: ViewModel() {

    private val _uploadedImage = mutableStateOf<Uri?>(null)
    val uploadedImage: Uri? get() = _uploadedImage.value

    fun setImageUri(uri: Uri) {
        _uploadedImage.value = uri
    }

    fun removeImageUri() {
        _uploadedImage.value = null
    }

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var servingSize by mutableStateOf("")
        private set

    var cookTime by mutableStateOf("")
        private set

    lateinit var recipe: Recipe


    fun addTitle(newTitle: String) {
        title = newTitle
    }

    fun addDescription(newDescription: String) {
        description = newDescription
    }

    fun addServingSize(newServingSize: String) {
        servingSize = newServingSize
    }

    fun addCookTime(newCookTime: String) {
        cookTime = newCookTime
    }


    private val _categories = mutableStateListOf<toggleableCategory>()
    val categories: List<toggleableCategory> = _categories

    init {
        _categories.addAll(
            listOf(
                toggleableCategory(false, "Breakfast"),
                toggleableCategory(false, "Lunch"),
                toggleableCategory(false, "Dinner"),
                toggleableCategory(false, "Merienda"),
                toggleableCategory(false, "Sweet"),
                toggleableCategory(false, "Savory")
            )
        )
    }

    fun toggleCategory(index: Int) {
        _categories[index] = _categories[index].copy(isChecked = !_categories[index].isChecked)
    }

    val selectedCategoryNames: List<String>
        get() = _categories.filter { it.isChecked }.map { it.text }


    //Ingredient
    var overlayIngredientList by mutableStateOf(false)
        internal set

    private val _ingredientList = mutableStateListOf<Item.WithID>()
    val ingredientList: List<Item.WithID> = _ingredientList

    fun openIngredientList() {
        overlayIngredientList = true
    }

    fun closeIngredientList() {
        overlayIngredientList = false
    }

    fun addIngredient() {
        val nextId = (_ingredientList.maxOfOrNull { it.id } ?: 0) + 1
        _ingredientList.add(Item.WithID(nextId, ""))
    }

    fun removeIngredient(id: Int) {
        _ingredientList.removeAll { it.id == id }
    }

    fun updateIngredient(id: Int, newText: String) {
        val index = _ingredientList.indexOfFirst { it.id == id }
        val item = _ingredientList[index]
        item.let {
            _ingredientList[index] = it.copy(text = newText)
        }
    }

    fun reorderIngredients(fromIndex: Int, toIndex: Int) {
        val item = _ingredientList.removeAt(fromIndex)
        _ingredientList.add(toIndex, item)
    }

    // Procedure
    var overlayProcedureList by mutableStateOf(false)
        internal set


    private val _procedureList = mutableStateListOf<Item.WithID>()
    val procedureList: List<Item.WithID> = _procedureList

    fun openProcedureList() {
        overlayProcedureList = true
    }

    fun closeProcedureList() {
        overlayProcedureList = false
    }


    fun addProcedure() {
        val nextId = (_procedureList.maxOfOrNull { it.id } ?: 0) + 1
        _procedureList.add(Item.WithID(nextId, ""))
    }

    fun removeProcedure(id: Int) {
        _procedureList.removeAll { it.id == id }
    }

    fun updateProcedure(id: Int, newText: String) {
        val index = _procedureList.indexOfFirst { it.id == id }
        val item = _ingredientList[index]
        item.let {
            _procedureList[index] = it.copy(text = newText)
        }
    }

    fun reorderProcedure(fromIndex: Int, toIndex: Int) {
        val item = _procedureList.removeAt(fromIndex)
        _procedureList.add(toIndex, item)
    }

    fun uploadRecipe(): Recipe {
        val recipe = Recipe(
            image = ImageData.UriVal(uploadedImage),
            title = title,
            description = description,
            servingSize = servingSize,
            cookTime = cookTime,
            category = selectedCategoryNames,
            ingredients = _ingredientList,
            instructions = _procedureList
        )
        return recipe
    }
}
