package com.example.gamesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gamesapp.R

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val itemClicked = intent.getIntExtra("game", 0)
    }
}

