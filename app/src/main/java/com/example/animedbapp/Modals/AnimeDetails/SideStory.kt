package com.example.animedbapp.Modals.AnimeDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SideStory (

    @SerializedName("mal_id")
    @Expose
    var malId: Int? = null,
    @SerializedName("type")
    @Expose
    var type: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null

)