package com.example.animedbapp.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animedbapp.Networking.RetrofitClient
import com.example.animedbapp.R
import com.example.animedbapp.onBookmarksResultsListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_search_watchlist.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookmarkAdapter(private val dataWatchList:ArrayList<Int>): RecyclerView.Adapter<BookmarkAdapter.ViewHolder>() {

    var  Myonclicklistener: onBookmarksResultsListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.layout_search_watchlist, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataWatchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataWatchList[position]
          holder.itemView.CvsearchRes.setOnClickListener {
              Myonclicklistener?.BookmarksResultsDeatilsonClick(item)
          }

        holder.bind(item)
    }

  inner  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),CoroutineScope {
      override val coroutineContext: CoroutineContext
          get() = Dispatchers.Main

        fun bind(dataWatchList: Int) {
            launch {
                val pApi = RetrofitClient.AnimeAPI
                Log.i("call","this is here")

                val response = pApi.getAnimePics(dataWatchList)
                val animeWatchList= response.body()
                with(itemView) {
                    Picasso.get().load(animeWatchList?.imageUrlRelated).into(animeimageSW)
                    titleSWTv.text = animeWatchList?.Relatedtitle
                    ScoreSWTv.text = animeWatchList?.score.toString()
                    englishTitleSWTv.text = animeWatchList?.titleEnglish


                }

            }

        }

    }


}