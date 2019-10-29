package com.example.animedbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animedbapp.Adapters.ReviewAdapter
import com.example.animedbapp.Modals.Review
import com.example.animedbapp.Networking.RetrofitClient
import kotlinx.android.synthetic.main.activity_bookmarks.*
import kotlinx.android.synthetic.main.activity_reviews.*
import kotlinx.android.synthetic.main.layout__reviewlist.*
import kotlinx.android.synthetic.main.layout_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

class ReviewsActivity : AppCompatActivity() ,CoroutineScope{
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    val ReviewList=ArrayList<Review>()
    val reviewAdapter=ReviewAdapter(ReviewList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)
        setSupportActionBar(toolbarReviews)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        RvReviewsList.layoutManager=LinearLayoutManager(this)
        RvReviewsList.adapter=reviewAdapter
        val  malId=intent.getIntExtra("malId",1)
       LoadReviews(malId)



    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {

                onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun LoadReviews(malId: Int) {
        launch {
            val animeService=RetrofitClient.AnimeAPI
            val Reviews=animeService.getReviews(malId)
          val  reviewsList=Reviews.body()?.reviews
            if (reviewsList != null) {
                ReviewList.addAll(reviewsList)
            }
            reviewAdapter.notifyDataSetChanged()


        }

    }
}
