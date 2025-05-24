package com.example.hapag.viewModel

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hapag.model.Item
import com.example.hapag.model.RecipeEvent
import com.example.hapag.model.RecipeState
import com.example.hapag.model.toggleableCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UploadViewModel(
    state: RecipeState = RecipeState(),
    onEvent: (RecipeEvent) -> Unit): ViewModel() {

    private val _uploadedImage = mutableStateOf<Uri?>(null)
    val uploadedImage: Uri? get() = _uploadedImage.value

    fun setImageUri(uri: Uri) {
        _uploadedImage.value = uri
    }

    fun removeImageUri() {
        _uploadedImage.value = null
    }

    private val _title = mutableStateOf("")
    val title: State<String> = _title
    private val _description = mutableStateOf("")
    val description: State<String> = _description

    private val _servingSize = mutableStateOf("")
    val servingSize: State<String> = _servingSize

    private val _cookTime = mutableStateOf("")
    val cookTime: State<String> = _cookTime

    private val _categories1 = MutableStateFlow<List<toggleableCategory>>(emptyList())
    val categories1: StateFlow<List<toggleableCategory>> = _categories1

    private val _categories2 = MutableStateFlow<List<toggleableCategory>>(emptyList())
    val categories2: StateFlow<List<toggleableCategory>> = _categories2

    private val _ingredientList = mutableStateListOf<Item.WithID>()
    val ingredientList: List<Item.WithID> = _ingredientList

    private val _procedureList = mutableStateListOf<Item.WithID>()
    val procedureList: List<Item.WithID> = _procedureList


    fun uploadReset() {
        _title.value = ""
        _description.value = ""
        _servingSize.value = ""
        _cookTime.value = ""
        _uploadedImage.value = null
        _categories1.value = resetCategory1State()
        _categories2.value = resetCategory2State()
        _ingredientList.clear()
        _procedureList.clear()
    }


    fun addTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun addDescription(newDescription: String) {
        _description.value = newDescription
    }

    fun addServingSize(newServingSize: String) {
        _servingSize.value = newServingSize
    }

    fun addCookTime(newCookTime: String) {
        _cookTime.value = newCookTime
    }

    fun resetCategory1State(): List<toggleableCategory> {
        return listOf(
            toggleableCategory(false, "Breakfast"),
            toggleableCategory(false, "Lunch"),
            toggleableCategory(false, "Dinner"),
            toggleableCategory(false, "Merienda"),
        )
    }

    fun resetCategory2State(): List<toggleableCategory> {
        return listOf(
            toggleableCategory(false, "Sweet"),
            toggleableCategory(false, "Savory"),
        )
    }


    fun toggleCategory1(index: Int) {
        val currentCategories = _categories1.value.toMutableList()
        currentCategories[index] =
            currentCategories[index].copy(isChecked = !currentCategories[index].isChecked)
        _categories1.value = currentCategories
    }

    fun toggleCategory2(index: Int) {
        val currentCategories = _categories2.value.toMutableList()
        currentCategories[index] =
            currentCategories[index].copy(isChecked = !currentCategories[index].isChecked)
        _categories2.value = currentCategories
    }

    fun joinCategories(): List<String> {
        val selectedCategories1 = _categories1.value.filter { it.isChecked }.map { it.text }
        val selectedCategories2 = _categories2.value.filter { it.isChecked }.map { it.text }
        return selectedCategories1 + selectedCategories2
    }


    //Ingredient
    var overlayIngredientList by mutableStateOf(false)
        internal set


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
        val item = _procedureList[index]
        item.let {
            _procedureList[index] = it.copy(text = newText)
        }
    }

    fun reorderProcedure(fromIndex: Int, toIndex: Int) {
        val item = _procedureList.removeAt(fromIndex)
        _procedureList.add(toIndex, item)
    }

    fun ingcopyList(): List<Item.WithID> {
        val newIngredientList = _ingredientList.map { it.copy() }
        return newIngredientList
    }

    fun procopyList(): List<Item.WithID> {
        val newProcedureList = _ingredientList.map { it.copy() }
        return newProcedureList
    }
}


