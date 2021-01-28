package com.example.gamesapp.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesapp.R
import com.example.gamesapp.model.Game
import com.example.gamesapp.model.OnGameClickListener
import com.example.gamesapp.ui.GameActivity
import com.example.gamesapp.ui.MainActivity
import kotlinx.android.synthetic.main.game_icon.view.*

class GameAdapter(private var listGames: ArrayList<Game>, private val onGameClickListener: OnGameClickListener) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {

        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.game_icon, parent, false)

        return GameViewHolder(view)
    }


    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        val game = listGames[position]

        holder.GameCover.setImageResource(listGames[position].image)
        holder.GameTitle.text = listGames[position].title
        holder.GameYear.text = listGames[position].title


        holder.itemView.setOnClickListener {
          // mContext.startActivity(Intent(mContext, GameActivity::class.java))

            onGameClickListener.onGameItemClicked(position)

          // val intent = Intent(this, GameActivity::class.java)
            //startActivityForResult(intent)
        }
    }

    override fun getItemCount(): Int {
        return listGames.size
    }

   // fun addGame(list: ArrayList<Game>) {
       // listGames.addAll(list)
       // notifyDataSetChanged()
    //}

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var GameCover: ImageView = itemView.imgCover
        var GameTitle: TextView = itemView.txtTitle
        var GameYear: TextView = itemView.txtYear


    }

}