package com.example.gamesapp.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

//data class Game(var image: Int, var title: String, var year: String, var overview: String) :
//        Serializable {
//    override fun toString(): String = title
//}

class Game(
    var image: String? = "",
    var title: String? = "",
    var year: String? = "",
    var overview: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(overview)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Game> {
        override fun createFromParcel(parcel: Parcel): Game {
            return Game(parcel)
        }

        override fun newArray(size: Int): Array<Game?> {
            return arrayOfNulls(size)
        }
    }
}