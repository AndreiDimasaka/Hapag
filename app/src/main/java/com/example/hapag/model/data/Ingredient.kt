package com.example.hapag.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ingredient_table")
data class Ingredient(
    @ColumnInfo(name = "ingredient_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
)

@Entity(
    tableName = "recipe_ingredients",
    primaryKeys = ["recipeId", "ingredientId"],
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["recipe_id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Ingredient::class,
            parentColumns = ["ingredient_id"],
            childColumns = ["ingredientId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["recipeId"]),
        Index(value = ["ingredientId"])
    ]
)

data class RecipeIngredientCrossRef(
    val recipeId: Long,
    val ingredientId: Long,
)