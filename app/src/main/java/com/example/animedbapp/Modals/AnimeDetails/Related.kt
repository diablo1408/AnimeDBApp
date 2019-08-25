package com.example.animedbapp.Modals.AnimeDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Related (

    @SerializedName("Adaptation")
    @Expose
    var adaptation: List<Adaptation>? = null,
    @SerializedName("Side story")
    @Expose
    var sideStory: List<Adaptation>? = null,
    @SerializedName("Summary")
    @Expose
    var summary: List<Adaptation>? = null,
    @SerializedName("Other")
    @Expose
    var other : List<Adaptation>? = null,
     @SerializedName("Alternative version")
     @Expose
     var  alternativeVersion: List<Adaptation>?  = null,
     @SerializedName("Sequel")
     @Expose
      var sequel: List<Adaptation> ? = null,
    @SerializedName("Spin-off")
     @Expose
    var spinOff :List<Adaptation>?  = null

//


)