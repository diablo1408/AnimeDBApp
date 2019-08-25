package com.example.animedbapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animedbapp.Modals.AnimeDetails.CharactersDetails
import com.example.animedbapp.R
import com.example.animedbapp.onCharactersclickListeners
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_related.view.*

class CharacterAdapter(private val dataCharacters:ArrayList<CharactersDetails>): RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    var  Myonclicklistener: onCharactersclickListeners?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.layout_related, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataCharacters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataCharacters[position]
        holder.itemView.setOnClickListener {
            Myonclicklistener?.CharactersPageopenonClick(item)
        }


        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(dataCharacter: CharactersDetails) {

            with(itemView) {

                Picasso.get().load(dataCharacter.imageUrl).into(ivRPics)
              val  Cname= dataCharacter.name?.substringAfter(",")+ " "+dataCharacter.name?.substringBefore(",")
                titleRTv.text=Cname
                 scoretextR.visibility=View.GONE
                 btnRScore.visibility=View.GONE
                 epRTv.visibility=View.GONE

            }

        }

    }


}