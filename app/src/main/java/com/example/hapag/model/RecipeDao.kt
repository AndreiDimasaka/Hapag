package com.example.hapag.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Upsert
    fun upsertRecipe(recipe: Recipe)

    @Delete
    fun deleteRecipe(recipe: Recipe)

    @Query("SELECT * FROM recipe_table WHERE category LIKE '%Breakfast%'")
    fun getBreakFastRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE category LIKE '%Merienda%'")
    fun getMeriendaRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE category LIKE '%Lunch%'")
    fun getLunchRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE category LIKE '%Dinner%'")
    fun getDinnerRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE category LIKE '%Sweet%'")
    fun getSweetRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE category LIKE '%Savory%'")
    fun getSavoryRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE category LIKE '%Breakfast%' OR category LIKE '%Merienda%' OR category LIKE '%Lunch%' OR category LIKE '%Dinner%' AND category LIKE '%Sweet%'")
    fun getAnySweetRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM recipe_table WHERE category LIKE '%Breakfast%' OR category LIKE '%Merienda%' OR category LIKE '%Lunch%' OR category LIKE '%Dinner%' AND category LIKE '%Savory%'")
    fun getAnySavoryRecipes(): Flow<List<Recipe>>


    @Query("SELECT * FROM recipe_table")
    fun getAllRecipes(): Flow<List<Recipe>>



}