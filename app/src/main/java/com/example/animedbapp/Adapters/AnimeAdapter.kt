package com.example.animedbapp.Adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animedbapp.Modals.DataPopular
import com.example.animedbapp.onclicklistener
import com.example.animedbapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_discover.view.*

class AnimeAdapter(private val dataAnime:ArrayList<DataPopular>): RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {
    var  Myonclicklistener: onclicklistener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.layout_discover, parent, false)
        
        return ViewHolder(view)
    }

    override fun getItemCount() = dataAnime.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataAnime[position]
        holder.itemView.RlAnime.setOnClickListener {
            Log.d("TAG2","this clicked")
            Log.i("PUI","this clicked")
            Myonclicklistener?.detailsonClick(item)

        }


        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(dataAnime: DataPopular) {

            with(itemView) {

                Picasso.get().load(dataAnime.imageUrl).into(ivPics)
                if(dataAnime.score!=null){
                    btnScore.text=dataAnime.score.toString()
                }
                else{
                    btnScore.visibility=View.GONE
                    Scoretext.visibility=View.GONE
                }
                if(dataAnime.episodes!=null){
                    statusDTv.text=dataAnime.episodes.toString()+"eps"
                }
                else{
                    statusDTv.visibility=View.GONE
                }



                titleTv.text=dataAnime.title.toString()

            }

        }

    }


}