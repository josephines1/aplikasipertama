package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasipertama.model.Student
import com.example.aplikasipertama.utils.Resource
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val listViewModel: ListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_list)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val actionBar = supportActionBar
        actionBar!!.title = "List Mahasiswa"

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
//        recyclerView.adapter = ListAdapter(this, students)
        listViewModel.getStudents().observe(this) {
            when (it) {
                is Resource.Success -> {
                    recyclerView.adapter = ListAdapter(this, it.data)
                }
                is Resource.Error -> {}
                Resource.Loading -> {}
            }
        }
        listViewModel.layoutState.observe(this) { layout ->
            when (layout) {
                LayoutState.LINEAR -> {
                    recyclerView.layoutManager = LinearLayoutManager(this)
                }
                LayoutState.GRID -> {
                    recyclerView.layoutManager = GridLayoutManager(this, 2)
                }
                else -> {}
            }
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        recyclerView.setHasFixedSize(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_layout) {
            listViewModel.changeLayout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}