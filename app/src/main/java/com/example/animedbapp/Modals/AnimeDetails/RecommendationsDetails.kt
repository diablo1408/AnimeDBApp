package com.example.animedbapp.Modals.AnimeDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RecommendationsDetails (


    @SerializedName("mal_id")
    @Expose
    var malId: Int? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null,
    @SerializedName("recommendation_url")
    @Expose
    var recommendationUrl: String? = null,
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("recommendation_count")
    @Expose
    var recommendationCount: Int? = null

)

