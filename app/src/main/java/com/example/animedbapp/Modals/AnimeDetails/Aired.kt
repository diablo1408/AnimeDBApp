package com.example.animedbapp.Modals.AnimeDetails

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

  data  class Aired (


      @SerializedName("from")
    @Expose
    var from: String? = null,
      @SerializedName("to")
    @Expose
    var to: String? = null,
      @SerializedName("prop")
    @Expose
    var prop: Prop? = null,
      @SerializedName("string")
    @Expose
    var airedIn: String? = null

)

