package com.example.animedbapp.Modals.AnimeDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Characters (


    @SerializedName("characters")
    @Expose
    var characters: List<CharactersDetails>? = null

)
