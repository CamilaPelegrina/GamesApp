package com.example.gamesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesapp.R
import com.example.gamesapp.model.Game
import com.example.gamesapp.model.OnGameClickListener
import kotlinx.android.synthetic.main.game_icon.view.*

class GameAdapter(private var items: ArrayList<Game>, private val onGameClickListener: OnGameClickListener) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {

        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.game_icon, parent, false)

        return GameViewHolder(view)
    }


    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        val game = items[position]

        holder.GameCover.setImageResource(items[position].image)
        holder.GameTitle.text = items[position].title
        holder.GameYear.text = items[position].title


        holder.itemView.setOnClickListener {

            onGameClickListener.onGameItemClicked(position)

        }
    }

    override fun getItemCount(): Int {
        return items.size
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