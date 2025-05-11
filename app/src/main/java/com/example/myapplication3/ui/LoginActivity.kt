package com.example.myapplication3.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication3.data.BudgetDatabase
import com.example.myapplication3.data.User
import com.example.myapplication3.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: BudgetDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = BudgetDatabase.getDatabase(this)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val user = database.userDao().getUser(username, password)
                if (user != null) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java).apply {
                        putExtra("userId", user.id)
                    })
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.registerButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val existingUser = database.userDao().getUserByUsername(username)
                if (existingUser != null) {
                    Toast.makeText(this@LoginActivity, "Username already exists", Toast.LENGTH_SHORT).show()
                    return@launch
                }

                val user = User(username = username, password = password)
                database.userDao().insertUser(user)
                Toast.makeText(this@LoginActivity, "Registration successful", Toast.LENGTH_SHORT).show()
            }
        }
    }
} 