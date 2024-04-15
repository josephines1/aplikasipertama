package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplikasipertama.model.Student

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val name = intent.getStringExtra("NAME")
//        val major = intent.getStringExtra("MAJOR")
        val student: Student? = intent.getParcelableExtra("STUDENT")

        val tvName = findViewById<TextView>(R.id.tv_detail_name)
        val tvMajor = findViewById<TextView>(R.id.tv_detail_major)

        tvName.text = student?.name
        tvMajor.text = student?.major

        val btnEdit = findViewById<Button>(R.id.btn_edit)

        // Handle klik tombol Edit
        btnEdit.setOnClickListener {

            // Buat Intent untuk membuka EditActivity
            val intent = Intent(this, EditActivity::class.java)

            // Sertakan data siswa yang ingin diedit ke dalam intent
            intent.putExtra("STUDENT", student)

            // Mulai EditActivity
            startActivity(intent)
        }
    }
}