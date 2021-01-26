package com.example.gamesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_GamesApp)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            callMain()
        }

        findViewById<TextView>(R.id.txtRegister).setOnClickListener {
            callCadastro()
        }
    }

    private fun callMain(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun callCadastro(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}