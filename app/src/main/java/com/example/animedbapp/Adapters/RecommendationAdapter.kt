package com.example.animedbapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animedbapp.Modals.AnimeDetails.RecommendationsDetails
import com.example.animedbapp.R
import com.example.animedbapp.onRecomdclickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_related.view.*

class RecommendationAdapter(private val dataRecom:ArrayList<RecommendationsDetails>): RecyclerView.Adapter<RecommendationAdapter.ViewHolder>() {
    var  Myonclicklistener: onRecomdclickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.layout_related, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataRecom.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataRecom[position]
             holder.itemView.RLRAnime.setOnClickListener {
                 Myonclicklistener?.RecomdDeatilsonClick(item)
             }

        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(dataRecom: RecommendationsDetails) {

            with(itemView) {

                Picasso.get().load(dataRecom.imageUrl).into(ivRPics)
                titleRTv.text=dataRecom.title
                btnRScore.visibility=View.GONE
                scoretextR.visibility=View.GONE
                epRTv.visibility=View.GONE

            }

        }

    }


}