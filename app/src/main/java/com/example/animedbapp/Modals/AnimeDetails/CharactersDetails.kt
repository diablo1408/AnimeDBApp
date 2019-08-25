package com.example.animedbapp.Modals.AnimeDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharactersDetails (


    @SerializedName("mal_id")
    @Expose
    var malId: Int? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("role")
    @Expose
    var role: String? = null


)

