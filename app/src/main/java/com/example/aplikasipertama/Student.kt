package com.example.aplikasipertama

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(val name: String,
                   val major: String) : Parcelable
