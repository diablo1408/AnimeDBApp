package com.example.animedbapp.Adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animedbapp.Modals.AnimeDetails.Adaptation

import com.example.animedbapp.Networking.RetrofitClient
import com.example.animedbapp.R
import com.example.animedbapp.onRelatedclickListener
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.layout_related.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class RelatedAdapter(private val dataAnimeRelated:ArrayList<Adaptation>): RecyclerView.Adapter<RelatedAdapter.ViewHolder>(),CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    var  Myonclicklistener: onRelatedclickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.layout_related, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataAnimeRelated.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataAnimeRelated[position]
        holder.itemView.RLRAnime.setOnClickListener {
            Myonclicklistener?.RelateddetailsonClick(item)
        }


        holder.bind(item)
    }

 inner   class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(RelatedItem: Adaptation) {
            launch {
                val pApi = RetrofitClient.AnimeAPI

                val response = pApi.getAnimePics(RelatedItem.malId!!)
                val animeRelated= response.body()

                with(itemView) {

                    Picasso.get().load(animeRelated?.imageUrlRelated).into(ivRPics)
                    btnRScore.text=animeRelated?.score.toString()
                    epRTv.text=animeRelated?.episodes.toString()+"eps"

                    titleRTv.text=animeRelated?.Relatedtitle


                }


            }


        }




    }


}