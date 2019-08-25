package com.example.animedbapp.Modals.AnimeDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Recommendations (


    @SerializedName("recommendations")
    @Expose
    var recommendations: List<RecommendationsDetails>? = null

)
