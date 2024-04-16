package com.example.aplikasipertama.di

import androidx.room.Room
import com.example.aplikasipertama.data.room.StudentDatabase
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            StudentDatabase::class.java,
            "student_database"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    single { provideStudentDao(get()) }
}

val firebaseModule = module {
    single { Firebase.firestore }
}

fun provideStudentDao(db: StudentDatabase) = db.studentDao()