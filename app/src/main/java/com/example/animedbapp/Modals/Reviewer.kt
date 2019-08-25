package com.example.animedbapp.Modals

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Reviewer (

    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null,
    @SerializedName("username")
    @Expose
    var username: String? = null,
    @SerializedName("episodes_seen")
    @Expose
    var episodesSeen: Int? = null,
    @SerializedName("scores")
    @Expose
    var scores: Scores? = null

)
