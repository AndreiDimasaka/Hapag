package com.example.hapag.data

import android.net.Uri
import androidx.annotation.DrawableRes
import com.example.hapag.R


data class toggleableCategory(
    var isChecked: Boolean,
    val text: String
)


sealed class Screen(val route: String, @DrawableRes val icon: Int) {
    object Home : Screen("home", R.drawable.home_icon)
    object Upload : Screen("upload", R.drawable.baseline_file_upload_24)
    object Recipe : Screen("recipe", R.drawable.baseline_menu_book_24)
    object MyRecipes : Screen("myRecipes", R.drawable.baseline_menu_book_24)
    object Favorites : Screen("favorites", R.drawable.btn_3)

    companion object {
        val bottomNavScreens = listOf(Home, Upload, MyRecipes, Favorites)
    }
}

sealed class ImageData {
    data class DrawableRes(@androidx.annotation.DrawableRes val resId: Int) : ImageData()
    data class UriVal(val uri: Uri?) : ImageData()
    object Empty : ImageData()
}

sealed class Item{
    data class WithID(val id: Int, val text : String) : Item()
    data class Text(val text: String) : Item()

}

data class Recipe(
    var image: ImageData,
    val title: String,
    val description: String,
    val servingSize: String,
    val cookTime: String,
    val category: String,
    val ingredients: List<Item>,
    val instructions: List<Item>
)
