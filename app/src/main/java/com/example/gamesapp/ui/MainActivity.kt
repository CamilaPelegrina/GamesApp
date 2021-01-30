package com.example.gamesapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gamesapp.R
import com.example.gamesapp.adapters.GameAdapter
import com.example.gamesapp.model.OnGameClickListener
import com.example.gamesapp.model.RegisterViewModel
import com.example.gamesapp.model.RepositoryDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnGameClickListener {
    lateinit var gameAdapter: GameAdapter
//    lateinit var items: ArrayList<Game>

    private val repository = RepositoryDatabase()
    private val viewModel by viewModels<RegisterViewModel> {
        object : ViewModelProvider.Factory {
            override fun <VM : ViewModel?> create(modelClass: Class<VM>): VM {
                return RegisterViewModel(repository) as VM
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.connectDatabase()
        viewModel.getAllGamesDatabase()
        viewModel.items.observe(this) {
            gameAdapter.addGame(it)
            Log.i("Games", it.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var items = arrayListOf<Game>()

        gameAdapter = GameAdapter(this)
        //gameAdapter = GameAdapter(this)
        rv_recyclerHome.adapter = gameAdapter
        rv_recyclerHome.apply {
            layoutManager = GridLayoutManager(context, 2)
        }
        rv_recyclerHome.hasFixedSize()


        //gameAdapter.addGame(items)

        findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener{
            callRegisterGame()
        }

    }

//    fun addItem(list: ArrayList<Game>) {
//        items.clear()
//        items.addAll(list)
//    }

    override fun onGameItemClicked(position: Int) {
        val game = gameAdapter.items[position]
        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("cover", game.image)
        intent.putExtra("title", game.title)
        intent.putExtra("year", game.year)
        intent.putExtra("overview", game.overview)
        startActivity(intent)
    }

    private fun callRegisterGame(){
        val intent = Intent(this, RegisterGameActivity::class.java)
        startActivity(intent)
    }

}