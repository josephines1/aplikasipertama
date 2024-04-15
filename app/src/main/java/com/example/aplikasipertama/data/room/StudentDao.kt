package com.example.aplikasipertama.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.lifecycle.LiveData
import com.example.aplikasipertama.model.Student

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(student: StudentEntity)

    @Query("select * from students")
    fun getStudents(): List<StudentEntity>

    @Query("SELECT * FROM students WHERE name LIKE :name")
    fun getStudentByName(name: String): List<StudentEntity>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(student: StudentEntity)

    @Delete
    suspend fun delete(student: StudentEntity)
}