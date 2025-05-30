package com.example.hapag.model

import com.example.hapag.model.data.Category
import com.example.hapag.model.data.Ingredient
import com.example.hapag.model.data.Procedure
import com.example.hapag.model.data.Recipe
import com.example.hapag.model.data.RecipeCategoryCrossRef
import com.example.hapag.model.data.RecipeIngredientCrossRef
import com.example.hapag.model.data.RecipeProcedureCrossRef
import com.example.hapag.model.data.RecipeWithCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repository(
    private val recipeDao: RecipeDao,
){
    fun getRecipeById(recipeId: Long?): Flow<Recipe> = recipeDao.getRecipeById(recipeId)

    suspend fun getRecipeIngredients(recipeId: Long?): List<Ingredient> {
        return recipeDao.getRecipeIngredients(recipeId)
    }

    suspend fun getRecipeProcedures(recipeId: Long?): List<Procedure> {
        return recipeDao.getRecipeProcedures(recipeId)
    }

    suspend fun getRecipeCategories(recipeId: Long?): List<Category> {
            return recipeDao.getCategoriesForRecipe(recipeId)
    }

    fun getFavoriteRecipesWithCategories(): Flow<List<RecipeWithCategories>> = recipeDao.getFavoriteRecipesWithCategories()

    fun getUserRecipesWithCategories(): Flow<List<RecipeWithCategories>> = recipeDao.getUserRecipesWithCategories()

    fun getAllRecipesWithCategories(): Flow<List<RecipeWithCategories>> = recipeDao.getAllRecipesWithCategories()

    fun getRecipesWithSearch(query: String): Flow<List<Recipe>> = recipeDao.searchRecipes(query)

    suspend fun toggleFavorite(recipeId: Long?, isFavorite: Boolean) {
        recipeDao.setFavoriteStatus(recipeId, isFavorite)
    }

    fun getFilteredRecipes(
        category: String?,
        taste: String?
    ): Flow<List<RecipeWithCategories>> {
        return recipeDao.getAllRecipesWithCategories().map { recipes ->
            recipes.filter { recipeWithCat ->
                val categoryFilter = when {
                    category.isNullOrEmpty() || category == "All" -> true
                    else -> recipeWithCat.categories.any { it.name.equals(category, ignoreCase = true) }
                }

                val tasteFilter = when {
                    taste.isNullOrEmpty() || taste == "All" -> true
                    else -> recipeWithCat.categories.any { it.name.equals(taste, ignoreCase = true) }
                }

                categoryFilter && tasteFilter
            }
        }
    }



    private suspend fun insertRecipeWithIngredients(recipeId: Long, ingredients: List<String>) {
        ingredients.forEach { ingredientName ->
            var ingredientId = recipeDao.getIngredientIdByName(ingredientName)


            if (ingredientId == null) {
                val newIngredient = Ingredient(name = ingredientName)
                ingredientId = recipeDao.insertIngredient(newIngredient)
            }


            if (ingredientId != null) {
                val crossRef = RecipeIngredientCrossRef(
                    recipeId = recipeId,
                    ingredientId = ingredientId
                )
                recipeDao.insertRecipeIngredientCrossRef(crossRef)
            }
        }
    }

    private suspend fun insertProceduresForRecipe(
        recipeId: Long,
        procedures: List<String>
    ) {
        procedures.forEachIndexed{ index, instruction ->

            var procedureId = recipeDao.getProcedureIdByName(instruction)

            if (procedureId == null) {
                val procedure = Procedure(instructionText = instruction)
                procedureId = recipeDao.insertProcedures(procedure)
            }

            val crossRef = RecipeProcedureCrossRef(
                recipeId = recipeId,
                procedureId = procedureId,
                stepNumber = (index + 1).toLong()
            )
            recipeDao.insertProcedureCrossRefs(crossRef)
        }

    }

    private suspend fun insertCategoriesForRecipe(
        recipeId: Long,
        categories: List<String>
    ) {
        categories.forEach { categories ->
            var categoryId = recipeDao.getCategoryIdByName(categories)

            if (categoryId == null) {
                val category = Category(name = categories)
                categoryId = recipeDao.insertCategory(category)
            }

            // Create cross-reference
            if (categoryId != null) {
                val crossRef = RecipeCategoryCrossRef(
                        recipeId = recipeId,
                        categoryId = categoryId,
                )
                recipeDao.insertCategoryCrossRef(crossRef)
            }
        }
    }

    suspend fun insertFullRecipe(
        recipe: Recipe,
        ingredients: List<String>,
        procedures: List<String>,
        categories: List<String>
    ): Long {
        val recipeId = recipeDao.insertRecipe(recipe)
        insertCategoriesForRecipe(recipeId, categories)
        insertRecipeWithIngredients(recipeId, ingredients)
        insertProceduresForRecipe(recipeId, procedures)

        return recipeId
    }


    suspend fun deleteFullRecipe(
        recipeId: Long?
    ){
        recipeDao.deleteRecipe(recipeId)
        recipeDao.deleteRecipeIngredientCrossRef(recipeId)
        recipeDao.deleteRecipeProcedureCrossRef(recipeId)
        recipeDao.deleteRecipeCategoryCrossRef(recipeId)

    }
}