package com.example.animedbapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animedbapp.Modals.SearchResults
import com.example.animedbapp.R
import com.example.animedbapp.onSearchResultsListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_search_watchlist.view.*

class SearchResultAdapter(private val dataSearch:ArrayList<SearchResults>): RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    var  Myonclicklistener: onSearchResultsListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.layout_search_watchlist, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataSearch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataSearch[position]
         holder.itemView.CvsearchRes.setOnClickListener {
             Myonclicklistener?.SearchResultsDetailsonClick(item)
         }

        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(dataSearch:  SearchResults) {

            with(itemView) {
                Picasso.get().load(dataSearch.imageUrl).into(animeimageSW)
                titleSWTv.text = dataSearch.title
                ScoreSWTv.text = dataSearch.score.toString()
                englishTitleSWTv.text = dataSearch.episodes.toString()+"Eps"
                eTitletext.visibility=View.INVISIBLE
                btnRemoveList.visibility=View.INVISIBLE


            }

        }

    }


}