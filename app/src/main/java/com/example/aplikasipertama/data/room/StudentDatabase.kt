package com.example.aplikasipertama.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [StudentEntity::class],
    version = 3,
    exportSchema = false
)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao() : StudentDao
}