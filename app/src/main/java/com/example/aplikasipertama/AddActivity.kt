package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aplikasipertama.model.Student
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddActivity : AppCompatActivity() {

    private val addViewModel: AddViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_add)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val nameEd = findViewById<EditText>(R.id.ed_add_name)
        val majorEd = findViewById<EditText>(R.id.ed_add_major)
        val addBtn = findViewById<Button>(R.id.btn_add)
        addBtn.setOnClickListener {
            val student = Student(
                name = nameEd.text.ifEmpty { "" }.toString(),
                major = majorEd.text.ifEmpty { "" }.toString()
            )
            addViewModel.addStudent(student)
        }
        addViewModel.studentAdded.observe(this) {
            if (it) {
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
//                finish()
            }
        }
    }
}