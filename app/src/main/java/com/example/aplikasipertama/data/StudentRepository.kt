package com.example.aplikasipertama.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.aplikasipertama.data.room.StudentDao
import com.example.aplikasipertama.data.room.toModel
import com.example.aplikasipertama.model.Student
import com.example.aplikasipertama.model.toEntity
import com.example.aplikasipertama.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StudentRepository(
    private val studentDao: StudentDao
) {
    suspend fun insert(student: Student) : Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        try {
            studentDao.insert(student.toEntity())
            emit(Resource.Success(Unit))
        } catch (e: Error) {
            Log.e("StudentRepository: Insert", e.toString())
            emit(Resource.Error(e.toString()))
        }
    }

    fun getStudents(): LiveData<Resource<List<Student>>> = liveData {
        emit(Resource.Loading)
        try {
            val students = studentDao.getStudents().map { it.toModel() }
            emit(Resource.Success(students))
        } catch (e: Error) {
            Log.e("StudentRepository: getStudents", e.toString())
            emit(Resource.Error(e.toString()))
        }
    }

    suspend fun update(student: Student): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        try {
            studentDao.update(student.toEntity())
            emit(Resource.Success(Unit))
        } catch (e: Error) {
            Log.e("StudentRepository: update", e.toString())
            emit(Resource.Error(e.toString()))
        }
    }

    suspend fun delete(student: Student): LiveData<Resource<Unit>> = liveData {
        emit(Resource.Loading)
        try {
            studentDao.delete(student.toEntity())
            emit(Resource.Success(Unit))
        } catch (e: Error) {
            Log.e("StudentRepository: delete", e.toString())
            emit(Resource.Error(e.toString()))
        }
    }
}