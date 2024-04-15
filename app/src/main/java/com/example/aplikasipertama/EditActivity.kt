package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplikasipertama.R
import com.example.aplikasipertama.model.Student
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditActivity : AppCompatActivity() {

    private val updateViewModel: UpdateViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_edit)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val student: Student? = intent.getParcelableExtra("STUDENT")

        val tvName = findViewById<TextView>(R.id.tv_name)
        val etMajor = findViewById<EditText>(R.id.et_edit_major)
        val btnSave = findViewById<Button>(R.id.btn_save)

        // Set data awal siswa ke dalam TextView dan EditText
        tvName.text = student?.name
        etMajor.setText(student?.major)

        // Ketika tombol Simpan
        btnSave.setOnClickListener {
            val studentInput = Student(
                name = tvName.text.ifEmpty { "" }. toString(),
                major = etMajor.text.ifEmpty { "" }.toString()
            )
            updateViewModel.updateStudent(studentInput)
        }
        updateViewModel.studentUpdated.observe(this) {
            if (it) {
//                finish()
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}