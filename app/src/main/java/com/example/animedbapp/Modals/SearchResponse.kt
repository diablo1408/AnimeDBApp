package com.example.animedbapp.Modals

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponse (


    @SerializedName("results")
    @Expose
    var resultsList: List<SearchResults>? = null,
    @SerializedName("last_page")
    @Expose
    var lastPage: Int? = null

):Serializable
