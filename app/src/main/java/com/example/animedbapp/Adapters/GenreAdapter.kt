package com.example.animedbapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animedbapp.Modals.AnimeDetails.Genre
import com.example.animedbapp.R
import kotlinx.android.synthetic.main.layout_genre.view.*

class GenreAdapter(private val dataGenre:ArrayList<Genre>): RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.layout_genre, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataGenre.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataGenre[position]


        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(dataGenre: Genre) {

            with(itemView) {

                btnGenre.text=dataGenre.name

            }

        }

    }


}