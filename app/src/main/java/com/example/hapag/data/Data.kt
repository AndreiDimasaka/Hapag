package com.example.hapag.data

<<<<<<< HEAD
import android.os.Parcelable import kotlinx.parcelize.Parcelize

@Parcelize data class Procedure( val step: Int, val description: String ) : Parcelable

@Parcelize data class Ingredient( val value: String ) : Parcelable

@Parcelize data class toggleableCategory( val text: String, val isChecked: Boolean ) : Parcelable

@Parcelize data class Recipe(val title: String, val description: String, val servingSize: String, val cookTime: String, val categories: List<String>, val ingredients: List<Ingredient>, val procedures: List<Procedure> ) : Parcelable
=======
data class Data(var counter: Int ) // Dummy class

data class Item(val id: Int, val text: String)
>>>>>>> parent of 38f4f3f (starting to route viewmodels to recipescreen, myrecipe etc. Updated uplaod screen)
