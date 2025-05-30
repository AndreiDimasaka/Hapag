package com.example.hapag.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hapag.model.data.Category
import com.example.hapag.model.data.Ingredient
import com.example.hapag.model.data.Procedure
import com.example.hapag.model.data.Recipe
import com.example.hapag.model.data.RecipeCategoryCrossRef
import com.example.hapag.model.data.RecipeIngredientCrossRef
import com.example.hapag.model.data.RecipeProcedureCrossRef


@Database(
    entities = [
        Recipe::class,
        Ingredient::class,
        Procedure::class,
        Category::class,
        RecipeCategoryCrossRef::class,
        RecipeIngredientCrossRef::class,
        RecipeProcedureCrossRef::class,
               ],
    version = 1,
    exportSchema = false
)
@TypeConverters(ImageConverter::class)
abstract class RecipeDataBase: RoomDatabase() {

    abstract fun recipeDao(): RecipeDao


    companion object{
        @Volatile
        private var INSTANCE: RecipeDataBase? = null

        fun getDatabase(context: Context): RecipeDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDataBase::class.java,
                    "recipe_database"
                ).createFromAsset(databaseFilePath = "database/final recipes.db").build()
                INSTANCE = instance
                return instance
            }

        }
    }
}