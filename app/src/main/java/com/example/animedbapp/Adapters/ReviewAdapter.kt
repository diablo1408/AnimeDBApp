package com.example.animedbapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.animedbapp.Modals.AnimeDetails.Genre
import com.example.animedbapp.Modals.Review
import com.example.animedbapp.R
import kotlinx.android.synthetic.main.layout__reviewlist.view.*
import kotlinx.android.synthetic.main.layout_info.view.*


class ReviewAdapter(private val dataReview:ArrayList<Review>): RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val li = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = li.inflate(R.layout.layout__reviewlist, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = dataReview.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataReview[position]

       holder.itemView.readmoreReTv.setOnClickListener {
           holder.itemView.apply {
               readmoreReTv.visibility = View.GONE
               arrowUp.visibility = View.VISIBLE
               ReviewTv.maxLines = Integer.MAX_VALUE
           }

        }
        holder.itemView.arrowUpRe.setOnClickListener {
            holder.itemView.apply {
                readmoreReTv.visibility = View.VISIBLE
                arrowUp.visibility = View.GONE
                ReviewTv.maxLines = 5
            }

        }
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(dataReview: Review) {

            with(itemView) {

             ReviewTv.text=dataReview.content
                usernameTv.text=dataReview.reviewer?.username

            }

        }

    }


}