
package com.example.hapag.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "category_table")
data class Category(
    @ColumnInfo(name = "category_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
)

@Entity(
    tableName = "recipe_categories",
    primaryKeys = ["recipeId", "categoryId"],
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["recipe_id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
            ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["category_id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
       Index(value = ["recipeId"]), Index(value = ["categoryId"])
    ]
)
data class RecipeCategoryCrossRef(
    val recipeId: Long,
    val categoryId: Long,
)
