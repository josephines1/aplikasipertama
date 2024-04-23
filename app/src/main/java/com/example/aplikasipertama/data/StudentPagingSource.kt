package com.example.aplikasipertama.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.aplikasipertama.model.Student
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class StudentPagingSource(private val firestoreDb: FirebaseFirestore) : PagingSource<QuerySnapshot, Student>() {

    override fun getRefreshKey(state: PagingState<QuerySnapshot, Student>): QuerySnapshot? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, Student> {
        return try {
            val ref = firestoreDb.collection("students")
            // Mengambil querySnapshot sekarang
            val currentPage = params.key ?: ref
                .orderBy("name")
                .limit(5)
                .get()
                .await()

            // Mengambil document terakhir dari querySnapshot sekarang
            val lastDocumentSnapshot = currentPage.documents[currentPage.size() - 1]

            // Mengambil querySnapshot selanjutnya
            val nextPage = ref
                .orderBy("name")
                .limit(5)
                .startAfter(lastDocumentSnapshot)
                .get()
                .await()

            LoadResult.Page(
                data = currentPage.toObjects(Student::class.java),
                prevKey = null, // Tidak ada halaman sebelumnya, jadi prevKey selalu null
                nextKey = nextPage, // QuerySnapshot untuk halaman berikutnya
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}