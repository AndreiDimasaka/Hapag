package com.example.hapag.model

import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hapag.R


data class toggleableCategory(
    var isChecked: Boolean,
    val text: String
)


sealed class Screen(val route: String, val title: String, @DrawableRes val icon: Int) {
    object Home : Screen("Home", "Home", R.drawable.home_icon)
    object Upload : Screen("Upload", "Upload", R.drawable.baseline_file_upload_24)
    object Recipe : Screen("Recipe/{recipeTitle}", "Recipe", R.drawable.baseline_menu_book_24)
    object MyRecipes : Screen("My Recipes", "My Recipes", R.drawable.baseline_menu_book_24)
    object Favorites : Screen("Favorites", "Favorites", R.drawable.btn_3)

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


@Entity(tableName = "recipe_table")
data class Recipe(
    var image: ImageData,
    val title: String,
    val description: String,
    val servingSize: Int,
    val cookTime: String,
    val category: List<String>,
    val ingredients: List<Item>,
    val instructions: List<Item>,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    )

