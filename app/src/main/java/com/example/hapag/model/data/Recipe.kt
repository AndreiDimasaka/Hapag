package com.example.hapag.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hapag.model.ImageConverter

@Entity(tableName = "recipe_table")
data class Recipe(
    @ColumnInfo(name = "recipe_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    @TypeConverters(ImageConverter::class)
    var image: String?,
    val title: String,
    val description: String,
    val servingSize: String,
    val cookTime: String,
    val isUserCreated: Boolean,
    val isFavorite: Boolean,
)