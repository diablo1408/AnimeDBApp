package com.example.animedbapp.Networking

import com.example.animedbapp.Modals.AnimeDetails.AnimeDetails
import com.example.animedbapp.Modals.AnimeDetails.Characters
import com.example.animedbapp.Modals.AnimeDetails.Recommendations
import com.example.animedbapp.Modals.DiscoverAnime
import com.example.animedbapp.Modals.RelatedAnimeDetails
import com.example.animedbapp.Modals.ReviewResponse
import com.example.animedbapp.Modals.SearchResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeApi{


    @GET("top/anime/{id}/{filter}")
     fun getPICS(@Path("id")  id:Int,@Path("filter")type:String): Call<DiscoverAnime>
    @GET("anime/{mal_id}")
    suspend  fun getAnimeDetails(@Path("mal_id") malId :Int):Response<AnimeDetails>
    @GET("anime/{mal_id}")
    suspend  fun getAnimePics(@Path("mal_id") malId :Int):Response<RelatedAnimeDetails>
    @GET("anime/{mal_id}/characters_staff")
    suspend fun  getCharactersPics(@Path("mal_id")malId: Int):Response<Characters>
    @GET("anime/{mal_id}/recommendations")
    suspend fun  getRecommendations(@Path("mal_id")malId: Int):Response<Recommendations>
    @GET("search/anime/?&page=1")
  suspend  fun getSearchResults(@Query("q")query: String):Response<SearchResponse>
    @GET("anime/{mal_id}/reviews")
    suspend fun getReviews(@Path (value = "mal_id") malId: Int ):Response<ReviewResponse>



}