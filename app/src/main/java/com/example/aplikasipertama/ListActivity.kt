package com.example.aplikasipertama

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val students = listOf(
            Student(
                name = "Josephine",
                major = "Sistem dan Teknologi Informasi"
            ),
            Student(
                name = "Secil",
                major = "Sistem dan Teknologi Informasi"
            ),
            Student(
                name = "Aida",
                major = "Sistem dan Teknologi Informasi"
            ),
            Student(
                name = "Tasya",
                major = "Sistem dan Teknologi Informasi"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_students)

        recyclerView.adapter = ListAdapter(students)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

    }
}