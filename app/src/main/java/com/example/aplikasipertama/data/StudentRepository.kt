package com.example.aplikasipertama.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.aplikasipertama.data.room.StudentDao
import com.example.aplikasipertama.data.room.toModel
import com.example.aplikasipertama.model.Student
import com.example.aplikasipertama.model.toEntity
import com.example.aplikasipertama.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class StudentRepository(
    private val studentDao: StudentDao,
    private val firestoreDb: FirebaseFirestore
) {
    suspend fun insert(student: Student) : Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        try {
//            studentDao.insert(student.toEntity())
            val ref = firestoreDb.collection("students")
            ref.add(student)
            emit(Resource.Success(Unit))
        } catch (e: Error) {
            Log.e("StudentRepository: Insert", e.toString())
            emit(Resource.Error(e.toString()))
        }
    }

    fun getStudents(): Flow<List<Student>> = flow {
//        val students = studentDao.getStudents().map { it.toModel() }
        val ref = firestoreDb.collection("students")
        val querySnapshot = ref.get().await()
        if (!querySnapshot.isEmpty) {
            emit(querySnapshot.toObjects(Student::class.java))
        }
    }

    suspend fun update(student: Student): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        try {
//            studentDao.update(student.toEntity())
            val ref = firestoreDb.collection("students")
            ref.document(student.id.orEmpty()).update(
                mapOf(
                    "name" to student.name,
                    "major" to student.major
                )
            )
            emit(Resource.Success(Unit))
        } catch (e: Error) {
            Log.e("StudentRepository: update", e.toString())
            emit(Resource.Error(e.toString()))
        }
    }

    suspend fun delete(student: Student): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading)
        try {
//            studentDao.delete(student.toEntity())
            val ref = firestoreDb.collection("students")
            ref.document(student.id.orEmpty()).delete()
            emit(Resource.Success(Unit))
        } catch (e: Error) {
            Log.e("StudentRepository: delete", e.toString())
            emit(Resource.Error(e.toString()))
        }
    }
}