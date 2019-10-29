package com.example.animedbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animedbapp.Networking.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WatchActivity : AppCompatActivity() ,CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch)
        val  malId=intent.getIntExtra("malId",1)
        loadepsiodelist(malId)
    }

    private fun loadepsiodelist(malId: Int) {
        launch {
            val animeService= RetrofitClient.AnimeAPI
            val animedetails=animeService.getAnimeDetails(malId)



        }

    }
}
