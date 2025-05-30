package com.example.hapag.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "procedure_table")
data class Procedure(
    @ColumnInfo(name = "procedure_id")
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    val instructionText: String,
)

@Entity(
    tableName = "recipe_procedures",
    primaryKeys = ["recipeId", "procedureId"],
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["recipe_id"],
            childColumns = ["recipeId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Procedure::class,
            parentColumns = ["procedure_id"],
            childColumns = ["procedureId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["recipeId"]),
        Index(value = ["procedureId"])
    ]
)
data class RecipeProcedureCrossRef(
    val recipeId: Long,
    val procedureId: Long,
    val stepNumber: Long,
)