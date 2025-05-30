package com.example.hapag.viewModel

import android.net.Uri
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapag.Graph
import com.example.hapag.model.Repository
import com.example.hapag.model.data.Category
import com.example.hapag.model.data.CheckableCategory
import com.example.hapag.model.data.ImageData
import com.example.hapag.model.data.Ingredient
import com.example.hapag.model.data.Item
import com.example.hapag.model.data.Procedure
import com.example.hapag.model.data.Recipe
import com.example.hapag.model.data.RecipeState
import com.example.hapag.model.data.RecipeWithCategories
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val repository: Repository = Graph.repository
): ViewModel() {
    private val _uploadRecipeState = MutableStateFlow(RecipeState())
    val uploadRecipeState: StateFlow<RecipeState> = _uploadRecipeState.asStateFlow()

    private val _recipeState = MutableStateFlow<Recipe?>(null)
    val recipeState: StateFlow<Recipe?> = _recipeState.asStateFlow()

    private val _ingredientsState = MutableStateFlow<List<Ingredient>>(emptyList())
    val ingredientsState: StateFlow<List<Ingredient>> = _ingredientsState.asStateFlow()

    private val _instructionsState = MutableStateFlow<List<Procedure>>(emptyList())
    val instructionsState: StateFlow<List<Procedure>> = _instructionsState.asStateFlow()

    private val _categoriesState = MutableStateFlow<List<Category>>(emptyList())
    val categoriesState: StateFlow<List<Category>> = _categoriesState.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>("All")
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()


    private val _selectedTaste = MutableStateFlow<String?>("All")
    val selectedTaste: StateFlow<String?> = _selectedTaste.asStateFlow()

    private val _filteredRecipes = MutableStateFlow<List<RecipeWithCategories>>(emptyList())
    val filteredRecipes: StateFlow<List<RecipeWithCategories>> = _filteredRecipes.asStateFlow()


    private var currentRecipeId = mutableLongStateOf(0)
    val currentRecipeIdState: MutableLongState = currentRecipeId

    private val _favoritesWithCategories = MutableStateFlow<List<RecipeWithCategories>>(emptyList())
    val favoritesWithCategories: StateFlow<List<RecipeWithCategories>> = _favoritesWithCategories.asStateFlow()

    private val _userRecipesWithCategories = MutableStateFlow<List<RecipeWithCategories>>(emptyList())
    val userRecipesWithCategories: StateFlow<List<RecipeWithCategories>> = _userRecipesWithCategories.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()


    private val _uploadedImage = mutableStateOf<Uri?>(null)
    val uploadedImage: Uri? get() = _uploadedImage.value

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _description = mutableStateOf("")
    val description: State<String> = _description

    private val _servingSize = mutableStateOf("")
    val servingSize: State<String> = _servingSize

    private val _cookTime = mutableStateOf("")
    val cookTime: State<String> = _cookTime

    private val _category = MutableStateFlow<List<CheckableCategory>>(
        listOf<CheckableCategory>(
            CheckableCategory(1, "Breakfast", false),
            CheckableCategory(2, "Merienda", false),
            CheckableCategory(3, "Lunch", false),
            CheckableCategory(4, "Dinner", false)
        )
    )
    val category: StateFlow<List<CheckableCategory>> = _category

    private val _taste = MutableStateFlow<List<CheckableCategory>>(
            listOf<CheckableCategory>(
            CheckableCategory(1, "Sweet", false),
            CheckableCategory(2, "Savory", false)
        )
    )
    val taste: StateFlow<List<CheckableCategory>> = _taste

    private val _ingredientInputList = mutableStateListOf<Item.WithID>()
    val ingredientInputList: List<Item.WithID> = _ingredientInputList

    private val _procedureInputList = mutableStateListOf<Item.WithID>()
    val procedureInputList: List<Item.WithID> = _procedureInputList


    fun loadAllRecipes(){
        viewModelScope.launch {
            repository.getAllRecipesWithCategories().collect{
                _filteredRecipes.value = it
            }
        }
    }

    fun loadRecipe(recipeId: Long?) {
        viewModelScope.launch {
            repository.getRecipeById(recipeId).collect { recipe ->
                _recipeState.value = recipe
                _ingredientsState.value = repository.getRecipeIngredients(recipeId)
                _instructionsState.value = repository.getRecipeProcedures(recipeId)
                _categoriesState.value = repository.getRecipeCategories(recipeId)
            }
        }
    }

    fun loadFavorites() {
        viewModelScope.launch {
            repository.getFavoriteRecipesWithCategories().collect {
                _favoritesWithCategories.value = it
            }
        }
    }

    fun loadUserRecipes() {
        viewModelScope.launch {
            repository.getUserRecipesWithCategories().collect {
                _userRecipesWithCategories.value = it
            }
        }
    }

    fun setCategoryFilter(category: String?) {
        _selectedCategory.value = category
        loadFilteredRecipes()
    }

    fun setTasteFilter(taste: String?) {
        _selectedTaste.value = taste
        loadFilteredRecipes()
    }

    fun loadFilteredRecipes() {
        viewModelScope.launch {
            repository.getFilteredRecipes(
                category = _selectedCategory.value,
                taste = _selectedTaste.value
            ).collect { recipes ->
                _filteredRecipes.value = recipes
            }
        }
    }


    fun toggleFavorite(recipeId: Long?, isFavorite: Boolean) {
            viewModelScope.launch {
                repository.toggleFavorite(recipeId, isFavorite)
        }
    }


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val searchResults: StateFlow<List<Recipe>> =
        searchQuery.debounce(300L)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isBlank()) {
                flowOf(emptyList())
            } else {
                repository.getRecipesWithSearch(query)
            }
        }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = emptyList()
            )

    fun onSearchQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
    }

    fun deleteCompleteRecipe(recipeId: Long?) {
        viewModelScope.launch {
            repository.deleteFullRecipe(recipeId)
        }
    }


    fun uploadCompleteRecipe(): Long {
        viewModelScope.launch {
            try {
                val recipe = Recipe(
                    id = null,
                    title = _title.value,
                    description = _description.value,
                    cookTime = _cookTime.value,
                    servingSize = _servingSize.value,
                    image = _uploadedImage.value as ImageData?,
                    isUserCreated = true,
                    isFavorite =  false
                )

                val ingredients = _ingredientInputList.map { it.text }
                val procedures = _procedureInputList.map { it.text }
                val categories = _category.value.filter { it.isChecked  == true }.map { it.text } + _taste.value.filter { it.isChecked == true }.map { it.text }

                val recipeId = repository.insertFullRecipe(
                    recipe = recipe,
                    ingredients = ingredients,
                    procedures = procedures,
                    categories = categories
                )
                currentRecipeId.longValue = recipeId
            } catch (e: Exception) {
                ("Error uploading recipe: ${e.message}")
            }
        }
        return currentRecipeId.longValue
    }

    fun setImageUri(uri: Uri) {
        _uploadedImage.value = uri
    }

    fun removeImageUri() {
        _uploadedImage.value = null
    }

    fun uploadReset() {
        _title.value = ""
        _description.value = ""
        _servingSize.value = ""
        _cookTime.value = ""
        _uploadedImage.value = null
        _category.value = resetCategoryState()
        _taste.value = resetTasteState()
        _ingredientInputList.clear()
        _procedureInputList.clear()
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

    init{
        _category.value =listOf<CheckableCategory>(
            CheckableCategory(1, "Breakfast", false),
            CheckableCategory(2, "Lunch", false),
            CheckableCategory(3, "Dinner", false),
            CheckableCategory(4, "Merienda", false)
        )
        _taste.value = listOf<CheckableCategory>(
            CheckableCategory(1, "Sweet", false),
            CheckableCategory(2, "Savory", false)
        )
    }

    private fun resetCategoryState(): List<CheckableCategory> {
        return listOf(
            CheckableCategory(1, "Breakfast", false),
            CheckableCategory(2, "Lunch", false),
            CheckableCategory(3, "Dinner", false),
            CheckableCategory(4, "Merienda", false)
        )
    }

    private fun resetTasteState(): List<CheckableCategory> {
        return listOf(
            CheckableCategory(1, "Sweet", false),
            CheckableCategory(2, "Savory", false)
        )
    }

    fun toggleCategory1(index: Int) {
        val currentCategories = _category.value.toMutableList()
        currentCategories[index] = currentCategories[index].copy(isChecked = !currentCategories[index].isChecked)
        _category.value = currentCategories
    }

    fun toggleCategory2(index: Int) {
        val currentTastes = _taste.value.toMutableList()
        if (index >= 0 && index < currentTastes.size) {
            currentTastes[index] = currentTastes[index].copy(isChecked = !currentTastes[index].isChecked)
            _taste.value = currentTastes
        }
    }

    // Ingredient
    var overlayIngredientList by mutableStateOf(false)
        internal set

    fun openIngredientList() {
        overlayIngredientList = true
    }

    fun closeIngredientList() {
        overlayIngredientList = false
    }

    fun addIngredient() {
        val nextId = (_ingredientInputList.maxOfOrNull { it.id } ?: 0) + 1
        _ingredientInputList.add(Item.WithID(nextId, ""))
    }

    fun removeIngredient(id: Int) {
        _ingredientInputList.removeAll { it.id == id }
    }

    fun updateIngredient(id: Int, newText: String) {
        val index = _ingredientInputList.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = _ingredientInputList[index]
            _ingredientInputList[index] = item.copy(text = newText)
        }
    }

    fun reorderIngredients(fromIndex: Int, toIndex: Int) {
        val item = _ingredientInputList.removeAt(fromIndex)
        _ingredientInputList.add(toIndex, item)
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
        val nextId = (_procedureInputList.maxOfOrNull { it.id } ?: 0) + 1
        _procedureInputList.add(Item.WithID(nextId, ""))
    }

    fun removeProcedure(id: Int) {
        _procedureInputList.removeAll { it.id == id }
    }

    fun updateProcedure(id: Int, newText: String) {
        val index = _procedureInputList.indexOfFirst { it.id == id }
        if (index != -1) {
            val item = _procedureInputList[index]
            _procedureInputList[index] = item.copy(text = newText)
        }
    }

    fun reorderProcedure(fromIndex: Int, toIndex: Int) {
        val item = _procedureInputList.removeAt(fromIndex)
        _procedureInputList.add(toIndex, item)
    }
}