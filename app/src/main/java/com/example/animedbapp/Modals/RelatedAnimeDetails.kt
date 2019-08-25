package com.example.animedbapp.Modals

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RelatedAnimeDetails(
    @SerializedName("image_url")
    @Expose
    var imageUrlRelated: String? = null,
    @SerializedName("title")
    @Expose
    var Relatedtitle: String? = null,
    @SerializedName("score")
    @Expose
    var score: Double? = null,
    @SerializedName("episodes")
    @Expose
    var episodes: Int? = null,
    @SerializedName("title_english")
    @Expose
     var titleEnglish: String? = null
)