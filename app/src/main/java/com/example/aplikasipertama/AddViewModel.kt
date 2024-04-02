package com.example.aplikasipertama

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasipertama.data.StudentRepository
import com.example.aplikasipertama.model.Student
import com.example.aplikasipertama.utils.Resource
import kotlinx.coroutines.launch

class AddViewModel(private val repository: StudentRepository) : ViewModel() {

    private val _studentAdded = MutableLiveData(false)
    val studentAdded: LiveData<Boolean>
        get() = _studentAdded

    fun addStudent(student: Student) {
        viewModelScope.launch {
            repository.insert(student).collect {
                when (it) {
                    is Resource.Error -> {
                        _studentAdded.value = false
                        Log.e("AddViewModel: Insert", it.error)
                    }
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        _studentAdded.value = true
                    }
                }
            }
        }
    }
}