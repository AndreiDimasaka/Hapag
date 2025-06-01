package com.example.hapag.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.hapag.model.data.Category
import com.example.hapag.model.data.Ingredient
import com.example.hapag.model.data.Procedure
import com.example.hapag.model.data.Recipe
import com.example.hapag.model.data.RecipeCategoryCrossRef
import com.example.hapag.model.data.RecipeIngredientCrossRef
import com.example.hapag.model.data.RecipeProcedureCrossRef
import com.example.hapag.model.data.RecipeWithCategories
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertRecipe(recipe: Recipe): Long

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertIngredient(ingredient: Ingredient): Long

    @Query("SELECT ingredient_id FROM ingredient_table WHERE name = :name")
    suspend fun getIngredientIdByName(name: String): Long?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertRecipeIngredientCrossRef(crossRef: RecipeIngredientCrossRef)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertProcedures(procedures: Procedure): Long

    @Query("SELECT MAX(procedure_id) FROM procedure_table")
    suspend fun getMaxProcedureId(): Long?

    @Query("SELECT procedure_id FROM procedure_table WHERE instructionText = :name")
    suspend fun getProcedureIdByName(name: String): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProcedureCrossRefs(crossRefs: RecipeProcedureCrossRef)


    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertCategory(category: Category): Long

    @Query("SELECT category_id FROM category_table WHERE name = :name")
    suspend fun getCategoryIdByName(name: String): Long?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertCategoryCrossRef(crossRef: RecipeCategoryCrossRef)


        @Transaction
        @Query(
            """
        SELECT * FROM recipe_table ORDER BY title ASC
    """
        )
        fun getAllRecipesWithCategories(): Flow<List<RecipeWithCategories>>



        @Query("DELETE FROM recipe_table WHERE recipe_id = :recipeId")
        suspend fun deleteRecipe(recipeId: Long?)

        @Query("DELETE FROM recipe_ingredients WHERE recipeId = :recipeId")
        suspend fun deleteRecipeIngredientCrossRef(recipeId: Long?)

        @Query("DELETE FROM recipe_procedures WHERE recipeId = :recipeId")
        suspend fun deleteRecipeProcedureCrossRef(recipeId: Long?)

        @Query("DELETE FROM recipe_categories WHERE recipeId = :recipeId")
        suspend fun deleteRecipeCategoryCrossRef(recipeId: Long?)


        @Query("SELECT * FROM recipe_table WHERE recipe_id = :recipeId")
        fun getRecipeById(recipeId: Long?): Flow<Recipe>


        @Transaction
        @Query(
            """
        SELECT * FROM recipe_table
        WHERE isFavorite = 1
    """
        )
        fun getFavoriteRecipesWithCategories(): Flow<List<RecipeWithCategories>>

        @Transaction
        @Query(
            """
        SELECT * FROM recipe_table
        WHERE isUserCreated = 1
    """
        )
        fun getUserRecipesWithCategories(): Flow<List<RecipeWithCategories>>


        @Query("UPDATE recipe_table SET isFavorite = :isFavorite WHERE recipe_id = :recipeId")
        suspend fun setFavoriteStatus(recipeId: Long?, isFavorite: Boolean)



    @Query("""
        SELECT * FROM recipe_table
        WHERE title LIKE '%' || :query || '%'
    """)
    fun searchRecipes(query: String): Flow<List<Recipe>>

        @Transaction
        @Query(
            """
        SELECT ingredient_table.* FROM ingredient_table
        INNER JOIN recipe_ingredients
        ON ingredient_table.ingredient_id = recipe_ingredients.ingredientId
        WHERE recipe_ingredients.recipeId = :recipeId
        ORDER BY ingredient_id ASC
    """
        )
        suspend fun getRecipeIngredients(recipeId: Long?): List<Ingredient>


        @Transaction
        @Query(
            """
        SELECT procedure_table.* FROM procedure_table
        INNER JOIN recipe_procedures
        ON procedure_table.procedure_id = recipe_procedures.procedureId
        WHERE recipe_procedures.recipeId = :recipeId
        ORDER BY procedure_id ASC
    """
        )
        suspend fun getRecipeProcedures(recipeId: Long?): List<Procedure>

        @Transaction
        @Query(
            """
        SELECT category_table.* FROM category_table
        INNER JOIN recipe_categories 
        ON category_table.category_id = recipe_categories.categoryId
        WHERE recipe_categories.recipeId= :recipeId
    """
        )
        suspend fun getCategoriesForRecipe(recipeId: Long?): List<Category>
    }



