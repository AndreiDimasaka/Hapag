package com.example.hapag.model.data

import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hapag.R

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        val TAG = "RoomMigration"
        Log.d(TAG, "Starting MIGRATION_2_3: Removing stepNumber from recipe_procedures table.")
        database.execSQL("""
            CREATE TABLE recipe_procedures_new (
                recipeId INTEGER NOT NULL,
                procedureId INTEGER NOT NULL,
                PRIMARY KEY(recipeId, procedureId),
                FOREIGN KEY(recipeId) REFERENCES recipe_table(recipe_id) ON UPDATE NO ACTION ON DELETE CASCADE,
                FOREIGN KEY(procedureId) REFERENCES procedure_table(procedure_id) ON UPDATE NO ACTION ON DELETE CASCADE
            )
        """.trimIndent())
        Log.d(TAG, "Created temporary table 'recipe_procedures_new' with updated schema and foreign keys.")

        database.execSQL("""
            INSERT INTO recipe_procedures_new (recipeId, procedureId)
            SELECT recipeId, procedureId
            FROM recipe_procedures
        """.trimIndent())
        Log.d(TAG, "Copied data from 'recipe_procedures' to 'recipe_procedures_new'.")
        database.execSQL("DROP TABLE recipe_procedures")
        Log.d(TAG, "Dropped old table 'recipe_procedures'.")
        database.execSQL("ALTER TABLE recipe_procedures_new RENAME TO recipe_procedures")
        Log.d(TAG, "Renamed 'recipe_procedures_new' to 'recipe_procedures'.")
        database.execSQL("""
            CREATE INDEX IF NOT EXISTS index_recipe_procedures_recipeId ON recipe_procedures (recipeId)
        """.trimIndent())
        database.execSQL("""
            CREATE INDEX IF NOT EXISTS index_recipe_procedures_procedureId ON recipe_procedures (procedureId)
        """.trimIndent())
        Log.d(TAG, "Recreated indices for 'recipe_procedures'.")
        Log.d(TAG, "MIGRATION_2_3 completed successfully.")
    }
}


data class RecipeState(
    val recipes: Recipe? = null,
    val image: ImageData = ImageData.DrawableRes(0),
    val title: String = "",
    val description: String = "",
    val cookTime: String = "",
    val servingSize: Int = 0,
    val isFavorite: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false,
)

sealed class Screen(val route: String, @DrawableRes val icon: Int) {
    object Home : Screen("Home",  R.drawable.home_icon)
    object Upload : Screen("Upload",  R.drawable.baseline_file_upload_24)
    object MyRecipes : Screen("My Recipes",  R.drawable.baseline_menu_book_24)
    object Favorites : Screen("Favorites",R.drawable.btn_3)

    companion object {
        val bottomNavScreens = listOf(Home, Upload, MyRecipes, Favorites)
    }
}


sealed class ImageData {
    data class DrawableRes(@androidx.annotation.DrawableRes val resId: Int) : ImageData()
    data class UriVal(val uri: Uri?) : ImageData()
    object Blank : ImageData()
}

sealed class Item {
    data class WithID(val id: Int, val text: String) : Item()
    data class Text(val text: String) : Item()
}

data class CheckableCategory(
    val id: Long,
    val text: String,
    var isChecked: Boolean,
)


data class RecipeWithCategories(
    @Embedded val recipe: Recipe,

    @Relation(
        parentColumn = "recipe_id",
        entityColumn = "category_id",
        associateBy = Junction(RecipeCategoryCrossRef::class,
            parentColumn = "recipeId",
            entityColumn = "categoryId"
        )
    )
    val categories: List<Category>
){
    constructor() : this(
        recipe = Recipe(
            id = 0,
            image = null,
            title = "",
            description = "",
            servingSize = "",
            cookTime = "",
            isUserCreated = false,
            isFavorite = false
        ),
        categories = emptyList()
    )
}




