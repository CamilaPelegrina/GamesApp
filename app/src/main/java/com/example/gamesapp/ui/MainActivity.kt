package com.example.gamesapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamesapp.R
import com.example.gamesapp.adapters.GameAdapter
import com.example.gamesapp.model.Game
import com.example.gamesapp.model.OnGameClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnGameClickListener {
    //lateinit var gameAdapter: GameAdapter
    lateinit var items: ArrayList<Game>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = arrayListOf(
            Game(R.drawable.splash, "HADES", "2020","TESTE"),
            Game(R.drawable.splash, "FALL", "2020","TESTE"),
            Game(R.drawable.splash, "STARDEW", "2015","TESTE"),
        )

        var gameAdapter = GameAdapter(items, this)
        //gameAdapter = GameAdapter(this)
        rv_recyclerHome.adapter = gameAdapter
        rv_recyclerHome.apply {
            layoutManager = GridLayoutManager(context, 2)
        }
        gameAdapter.notifyDataSetChanged()


        //gameAdapter.addGame(items)

    }

    override fun onGameItemClicked(position: Int) {
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("game", items[position])
        startActivity(intent)
    }

}