package com.example.animedbapp.Modals

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Review (

    @SerializedName("mal_id")
    @Expose
    var malId: Int? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,
    @SerializedName("helpful_count")
    @Expose
    var helpfulCount: Int? = null,
    @SerializedName("date")
    @Expose
    var date: String? = null,
    @SerializedName("reviewer")
    @Expose
    var reviewer: Reviewer? = null,
    @SerializedName("content")
    @Expose
    var content: String? = null

)