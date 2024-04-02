package com.example.aplikasipertama.di

import androidx.room.Room
import com.example.aplikasipertama.data.room.StudentDatabase
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

fun provideStudentDao(db: StudentDatabase) = db.studentDao()