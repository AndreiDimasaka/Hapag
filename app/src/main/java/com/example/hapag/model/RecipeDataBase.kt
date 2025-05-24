package com.example.hapag.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Recipe::class],
    version = 1
)
abstract class RecipeDataBase: RoomDatabase() {

    abstract val dao: RecipeDao
}