package com.example.animedbapp.Modals

import com.google.gson.annotations.SerializedName
data class DiscoverAnime (
    @SerializedName("top")

    val datapopular: ArrayList<DataPopular>?=null
)
