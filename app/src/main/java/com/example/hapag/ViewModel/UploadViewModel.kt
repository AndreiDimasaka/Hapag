package com.example.hapag.ViewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.hapag.data.ImageData
import com.example.hapag.data.Item
import com.example.hapag.data.Recipe
import com.example.hapag.data.toggleableCategory

class UploadViewModel: ViewModel() {
    val viewmodel = sharedViewModel()

    var uploadedImage by mutableStateOf<Uri?>(null)
        internal set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var servingSize by mutableStateOf("")
        private set

    var cookTime by mutableStateOf("")
        private set

    var category by mutableStateOf("")
        private set

    lateinit var recipe: Recipe

    fun addImage(uri: Uri) {
        uploadedImage = uri
    }

    fun addTitle(newTitle : String){
        title = newTitle
    }

    fun addDescription(newDescription : String){
        description = newDescription
    }

    fun addServingSize(newServingSize : String){
        servingSize = newServingSize
    }

    fun addCookTime(newCookTime : String){
        cookTime = newCookTime
    }

    fun category() {
        categoryCheckBox.forEach {
            it.isChecked = it.text + "," == category
        }
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

    private val _ingredientList = mutableStateListOf<Item>()
    val ingredientList: List<Item> = _ingredientList

    fun openIngredientList() {
        overlayIngredientList = true
    }

    fun closeIngredientList() {
        overlayIngredientList = false
    }

    fun addIngredient(item: Item) {
        val nextId = (_ingredientList.maxOfOrNull { (it as? Item.WithID?)?.id ?: 0 } ?: 0) + 1
        _ingredientList.add(Item.WithID(nextId, ""))
    }

    fun removeIngredient(id: Int, item: Item) {
        _ingredientList.removeAll { (it as? Item.WithID?)?.id == id }
    }

    fun updateIngredient(id: Int, newText: String) {
        val index = _ingredientList.indexOfFirst { (it as? Item.WithID?)?.id == id }
        if (index != -1) {
            val item = _ingredientList[index] as? Item.WithID?
            item?.let {
                _ingredientList[index] = it.copy(text = newText)
            }
        }
    }

    fun reorderIngredients(fromIndex: Int, toIndex: Int) {
        val item = _ingredientList.removeAt(fromIndex)
        _ingredientList.add(toIndex, item)
    }

    // Procedure
    var overlayProcedureList by mutableStateOf(false)
        internal set


    private val _procedureList = mutableStateListOf<Item>()
    val procedureList: List<Item> = _procedureList

    fun openProcedureList() {
        overlayProcedureList = true
    }

    fun closeProcedureList() {
        overlayProcedureList = false
    }


    fun addProcedure() {
        val nextId = (_procedureList.maxOfOrNull { (it as? Item.WithID?)?.id ?: 0 } ?: 0) + 1
        _procedureList.add(Item.WithID(nextId, ""))
    }

    fun removeProcedure(id: Int) {
        _procedureList.removeAll { (it as? Item.WithID?)?.id == id }
    }

    fun updateProcedure(id: Int, newText: String) {
        val index = _procedureList.indexOfFirst { (it as? Item.WithID?)?.id == id }
        if (index != -1) {
            val item = _procedureList[index] as? Item.WithID?
            item?.let {
                _procedureList[index] = it.copy(text = newText)
            }
        }
    }
        fun reorderProcedure(fromIndex: Int, toIndex: Int) {
            val item = _procedureList.removeAt(fromIndex)
            _procedureList.add(toIndex, item)
        }


        fun uploadRecipe() {
            category()
            recipe = Recipe(
                image = ImageData.UriVal(uploadedImage),
                title = title,
                description = description,
                servingSize = servingSize,
                cookTime = cookTime,
                category = category,
                ingredients = ingredientList,
                instructions = procedureList
            )
            viewmodel.selectRecipe(recipe)
        }
}