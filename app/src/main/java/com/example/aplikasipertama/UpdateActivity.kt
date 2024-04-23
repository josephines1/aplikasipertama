package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasipertama.model.Student
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateActivity : AppCompatActivity() {

    private val updateViewModel: UpdateViewModel by viewModel()

    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_update)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        student = intent.getParcelableExtra("STUDENT")

        val etName = findViewById<EditText>(R.id.et_edit_name)
        val etMajor = findViewById<EditText>(R.id.et_edit_major)
        val btnSave = findViewById<Button>(R.id.btn_save)

        // Set data awal siswa ke dalam EditText
        etName.setText(student?.name)
        etMajor.setText(student?.major)

        // Ketika tombol Simpan
        btnSave.setOnClickListener {
            val studentInput = Student(
                id = student?.id,
                name = etName.text.ifEmpty { "" }. toString(),
                major = etMajor.text.ifEmpty { "" }.toString()
            )
            updateViewModel.updateStudent(studentInput)
        }
        updateViewModel.studentUpdated.observe(this) {
            if (it) {
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
//                finish()
            }
        }
    }
}