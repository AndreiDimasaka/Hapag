package com.example.hapag.data

<<<<<<< HEAD
<<<<<<< HEAD
import android.os.Parcelable import kotlinx.parcelize.Parcelize
=======
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
>>>>>>> parent of e5dfdad (adding navcontroller)

data class Item(val id: Int, val text: String)

data class toggleableCategory(val isChecked: Boolean, val text: String)

<<<<<<< HEAD
@Parcelize data class toggleableCategory( val text: String, val isChecked: Boolean ) : Parcelable

@Parcelize data class Recipe(val title: String, val description: String, val servingSize: String, val cookTime: String, val categories: List<String>, val ingredients: List<Ingredient>, val procedures: List<Procedure> ) : Parcelable
=======
data class Data(var counter: Int ) // Dummy class

data class Item(val id: Int, val text: String)
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen)
=======
data class Recipe(var photo : Uri ,val title: String, val description: String, val servingSize: String, val cookTime: String, val category: String, val ingredients: List<String>, val instructions: List<String>)
>>>>>>> parent of e5dfdad (adding navcontroller)
