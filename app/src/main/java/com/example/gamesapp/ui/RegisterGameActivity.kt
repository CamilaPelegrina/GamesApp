package com.example.gamesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesapp.R

class RegisterGameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_game)
    }
}