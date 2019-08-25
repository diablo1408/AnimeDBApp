package com.example.animedbapp.Modals.AnimeDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Prop (

    @SerializedName("from")
    @Expose
    var from: From? = null,
    @SerializedName("to")
    @Expose
    var to: To? = null

)
