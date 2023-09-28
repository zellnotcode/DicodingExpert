package com.example.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DoaEntity::class], version = 1, exportSchema = false)
abstract class DoaDatabase : RoomDatabase() {

    abstract fun doaDao(): DoaDao
}