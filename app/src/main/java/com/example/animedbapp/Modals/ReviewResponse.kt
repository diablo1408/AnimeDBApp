package com.example.animedbapp.Modals

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewResponse (


    @SerializedName("reviews")
    @Expose
    var reviews: List<Review>? = null

)

