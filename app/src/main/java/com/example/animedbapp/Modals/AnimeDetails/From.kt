package com.example.animedbapp.Modals.AnimeDetails


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 data  class From (

    @SerializedName("day")
    @Expose
    var day: Int? = null,
    @SerializedName("month")
    @Expose
    var month: Int? = null,
    @SerializedName("year")
    @Expose
    var year: Int? = null

)
