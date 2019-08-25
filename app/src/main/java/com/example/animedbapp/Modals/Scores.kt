package com.example.animedbapp.Modals

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Scores (

    @SerializedName("overall")
    @Expose
    var overall: Int? = null,
    @SerializedName("story")
    @Expose
    var story: Int? = null,
    @SerializedName("animation")
    @Expose
    var animation: Int? = null,
    @SerializedName("sound")
    @Expose
    var sound: Int? = null,
    @SerializedName("character")
    @Expose
    var character: Int? = null,
    @SerializedName("enjoyment")
    @Expose
    var enjoyment: Int? = null

)
