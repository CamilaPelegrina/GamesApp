package com.example.gamesapp.model

import android.net.Uri
import java.io.Serializable

data class Game(var image: Int, var title: String, var year: String, var overview: String) :
        Serializable {
    override fun toString(): String = title
}