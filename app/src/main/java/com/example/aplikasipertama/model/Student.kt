package com.example.aplikasipertama.model

import android.os.Parcelable
import com.example.aplikasipertama.data.room.StudentEntity
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    @DocumentId val id: String? = null,
    val name: String,
    val major: String
) : Parcelable {
    constructor(): this(null, "", "")
}

fun Student.toEntity() = StudentEntity(
    id?.toLong(),
    name,
    major
)
