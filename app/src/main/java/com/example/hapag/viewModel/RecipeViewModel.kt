package com.example.hapag.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hapag.model.ImageData
import com.example.hapag.model.Recipe
import com.example.hapag.model.RecipeDao
import com.example.hapag.model.RecipeEvent
import com.example.hapag.model.RecipeState
import com.example.hapag.model.SortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class RecipeViewModel(
    private val dao: RecipeDao
): ViewModel() {

    private val _state = MutableStateFlow(RecipeState())
    private val _sortType = MutableStateFlow(SortType.All)
    private val _sortTaste = TODO()

    private val _recipes = _sortType
        .flatMapLatest {sortType ->
            when(sortType){
                SortType.Breakfast -> dao.getBreakFastRecipes()
                SortType.Merienda -> dao.getMeriendaRecipes()
                SortType.Lunch -> dao.getLunchRecipes()
                SortType.Dinner -> dao.getDinnerRecipes()
                SortType.Sweet -> dao.getSweetRecipes()
                SortType.Savory -> dao.getSavoryRecipes()
                else -> dao.getAllRecipes()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    val state = combine(_state, _sortType, _recipes){ state, sortType, recipes ->
        state.copy(
            recipes = recipes,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), RecipeState())

    fun onEvent(event: RecipeEvent){
        when(event){
            is RecipeEvent.DeleteRecipe -> {
                viewModelScope.launch {
                    dao.deleteRecipe(event.recipe)
                }
            }
            is RecipeEvent.SaveRecipe -> {
                val image = state.value.image
                val title = state.value.title
                val description = state.value.description
                val cookTime = state.value.cookTime
                val servingSize = state.value.servingSize
                val category = state.value.category
                val ingredients = state.value.ingredients
                val instructions = state.value.instructions

                if(title.isBlank() || image == ImageData.Blank || description.isBlank() || cookTime.isBlank() || servingSize == 0 || category.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()){
                    return
                }
                val recipe = Recipe(
                    image = image,
                    title = title,
                    description = description,
                    cookTime = cookTime,
                    servingSize = servingSize,
                    category = category,
                    ingredients = ingredients,
                    instructions = instructions
                )
                viewModelScope.launch {
                    dao.upsertRecipe(recipe)
                }
                _state.update {it.copy(
                    isAddingRecipe = false,
                    title = "",
                    description = "",
                    cookTime = "",
                    servingSize = 0,
                    category = emptyList(),
                    ingredients = emptyList(),
                    instructions = emptyList(),
                    image = ImageData.Blank
                )
                }
            }
            is RecipeEvent.SortRecipes -> {
                _sortType.value = event.sortType
            }

            is RecipeEvent.SetImage -> {
                _state.update { it.copy(
                    image = event.image
                ) }
            }
            is RecipeEvent.SetTitle -> {
                _state.update { it.copy(
                    title = event.title)
            }
            }
            is RecipeEvent.SetDescription -> {
                _state.update { it.copy(
                    description = event.description)
                }
            }
            is RecipeEvent.SetCookTime -> {
                _state.update { it.copy(
                    cookTime = event.cookTime)
                }
            }
            is RecipeEvent.SetServingSize -> {
                _state.update { it.copy(
                    servingSize = event.servingSize)
                }
            }
            is RecipeEvent.SetCategory -> {
                _state.update { it.copy(
                    category = event.category)
                }
            }
            is RecipeEvent.SetIngredients -> {
                _state.update { it.copy(
                    ingredients = event.ingredients)
                }
            }
            is RecipeEvent.SetInstructions -> {
                _state.update { it.copy(
                    instructions = event.instructions)
                }
            }
        }
    }
}
