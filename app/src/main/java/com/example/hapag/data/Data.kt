package com.example.hapag.data

import android.os.Parcelable import kotlinx.parcelize.Parcelize

@Parcelize data class Procedure( val step: Int, val description: String ) : Parcelable

@Parcelize data class Ingredient( val value: String ) : Parcelable

@Parcelize data class toggleableCategory( val text: String, val isChecked: Boolean ) : Parcelable

@Parcelize data class Recipe(val title: String, val description: String, val servingSize: String, val cookTime: String, val categories: List<String>, val ingredients: List<Ingredient>, val procedures: List<Procedure> ) : Parcelable