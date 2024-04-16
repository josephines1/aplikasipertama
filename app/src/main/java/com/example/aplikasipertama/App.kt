package com.example.aplikasipertama

import android.app.Application
import com.example.aplikasipertama.di.firebaseModule
import com.example.aplikasipertama.di.repositoryModule
import com.example.aplikasipertama.di.roomModule
import com.example.aplikasipertama.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(
                viewModelModule,
                repositoryModule,
                roomModule,
                firebaseModule
            ))
        }
    }
}