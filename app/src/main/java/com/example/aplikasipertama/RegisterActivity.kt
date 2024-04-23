package com.example.aplikasipertama

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        auth = Firebase.auth

        val edEmail = findViewById<EditText>(R.id.ed_register_email)
        val edPassword = findViewById<EditText>(R.id.ed_register_password)
        val edConfPassword = findViewById<EditText>(R.id.ed_register_conf_password)
        val btnRegister = findViewById<Button>(R.id.btn_register)

        btnRegister.setOnClickListener{
            val email = edEmail.text.toString()
            if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Email tidak cocok dan tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val password = edPassword.text.toString()
            if (password.isEmpty() && password.length < 8) {
                Toast.makeText(this, "Password tidak boleh kurang dari 8 dan tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val confPassword = edConfPassword.text.toString()
            if(password != confPassword) {
                Toast.makeText(this, "Konfirmasi password tidak sesuai", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.putExtra("message", "Akun Anda berhasil dibuat! Silahkan login dengan akun baru Anda.")
                    startActivity(intent)
                    finish()
                }
        }

        val btnToLogin = findViewById<TextView>(R.id.btn_to_login)

        btnToLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}