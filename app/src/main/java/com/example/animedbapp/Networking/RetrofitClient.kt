package com.example.animedbapp.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient{

    val AnimeAPI = retrofit()
        .create(AnimeApi::class.java)

    private fun retrofit() = Retrofit.Builder()
        .baseUrl("https://api.jikan.moe/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}