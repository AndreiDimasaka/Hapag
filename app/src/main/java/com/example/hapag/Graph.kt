package com.example.hapag

import android.content.Context
import com.example.hapag.model.RecipeDataBase
import com.example.hapag.model.Repository

object Graph {
    lateinit var database: RecipeDataBase
        private set

    val repository by lazy {
        Repository(
            recipeDao = database.recipeDao(),
        )
    }

    fun provide(context: Context) {
        database = RecipeDataBase.getDatabase(context)
    }
}

