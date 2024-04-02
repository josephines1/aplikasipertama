package com.example.aplikasipertama.data.room


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aplikasipertama.model.Student

@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey
    val name: String,
    val major: String
)

fun StudentEntity.toModel() = Student(
    name,
    major
)