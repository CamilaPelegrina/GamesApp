package com.example.gamesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.gamesapp.R
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.game_icon.view.*

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val cover = intent.getStringExtra("cover")
        val title = intent.getStringExtra("title")
        val year = intent.getStringExtra("year")
        val overview = intent.getStringExtra("overview")

//        imgCover.setImageResource(cover)
        Glide.with(this).load(cover.toString()).into(findViewById(R.id.imgCover))
        txtTitle.text = title
        txtYear.text = year
        txtOverview.text = overview


    }
}

