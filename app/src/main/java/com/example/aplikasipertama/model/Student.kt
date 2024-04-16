package com.example.aplikasipertama.model

import android.os.Parcelable
import com.example.aplikasipertama.data.room.StudentEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val id: Long? = null,
    val name: String,
    val major: String) : Parcelable

fun Student.toEntity() = StudentEntity(
    id,
    name,
    major
)
