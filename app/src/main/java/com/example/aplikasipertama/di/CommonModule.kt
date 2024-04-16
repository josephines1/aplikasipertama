package com.example.aplikasipertama.di

import android.widget.ListView
import com.example.aplikasipertama.AddViewModel
import com.example.aplikasipertama.ListViewModel
import com.example.aplikasipertama.UpdateViewModel
import com.example.aplikasipertama.data.StudentRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { AddViewModel(get()) }
    viewModel { UpdateViewModel(get()) }
}

val repositoryModule = module {
    single<StudentRepository> { StudentRepository(get(), get()) }
}