package com.example.gamesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamesapp.R
import com.example.gamesapp.model.Game
import com.example.gamesapp.model.OnGameClickListener
import kotlinx.android.synthetic.main.game_icon.view.*

class GameAdapter(private val onGameClickListener: OnGameClickListener) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    var items = arrayListOf<Game>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {

        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.game_icon, parent, false)

        return GameViewHolder(view)
    }

    fun addGame(list: ArrayList<Game>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        val game = items[position]

//        holder.GameCover.setImageResource(items[position].image)
        Glide.with(holder.itemView).load(game.image.toString()).into(holder.GameCover)
        holder.GameTitle.text = items[position].title
        holder.GameYear.text = items[position].year


        holder.itemView.setOnClickListener {

            onGameClickListener.onGameItemClicked(position)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var GameCover: ImageView = itemView.imgCover
        var GameTitle: TextView = itemView.txtTitle
        var GameYear: TextView = itemView.txtYear

    }

}