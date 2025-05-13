package com.example.hapag.data

import android.net.Uri
import androidx.compose.runtime.mutableStateOf

data class Item(val id: Int, val text: String)

data class toggleableCategory(val isChecked: Boolean, val text: String)

data class Recipe(var photo : Uri ,val title: String, val description: String, val servingSize: String, val cookTime: String, val category: String, val ingredients: List<String>, val instructions: List<String>)