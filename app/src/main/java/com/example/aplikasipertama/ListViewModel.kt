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

enum class LayoutState {
    LINEAR,
    GRID
}
class ListViewModel(private val repository: StudentRepository) : ViewModel() {
    private var _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>>
        get() = _students

    private val _layoutState = MutableLiveData<LayoutState>(LayoutState.LINEAR)

    val layoutState: LiveData<LayoutState>
        get() = _layoutState

    fun changeLayout() {
        if (layoutState.value == LayoutState.LINEAR) {
            _layoutState.value = LayoutState.GRID
        } else  {
            _layoutState.value = LayoutState.LINEAR
        }
    }

    fun getStudents() {
        viewModelScope.launch {
            repository.getStudents().collect {
                _students.value = it
            }
        }
    }

    fun delete(student: Student) {
        viewModelScope.launch {
            repository.delete(student).collect {
                when (it) {
                    is Resource.Error -> {}
                    Resource.Loading -> {}
                    is Resource.Success -> {
                        Log.d("ListViewModel: delete", "Success")
                    }
                }
            }
        }
    }
}