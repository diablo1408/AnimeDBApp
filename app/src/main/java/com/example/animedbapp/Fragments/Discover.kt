package com.example.animedbapp.Fragments


import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animedbapp.Adapters.AnimeAdapter
import com.example.animedbapp.AnimeDetailsActivity
import com.example.animedbapp.Modals.DataPopular
import com.example.animedbapp.Modals.DiscoverAnime
import com.example.animedbapp.Networking.RetrofitClient
import com.example.animedbapp.onclicklistener
import com.example.animedbapp.R

import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_popular.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class Popular : Fragment() {


    //override val coroutineContext: CoroutineContext
    // get() = Dispatchers.Main
//    private var scrollListener: EndlessRecyclerViewScrollListener? = null
    var startpage = 1
    var isScrolling = false
    val dataPopular = ArrayList<DataPopular>()

    val animeAdapter = AnimeAdapter(dataPopular)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_popular, contain1, false)
        val bundle = this.arguments
        val filter = bundle?.getString("type", "bypopularity")





        val GlayoutManager = GridLayoutManager(context, 2)


        root.RvPics.layoutManager = GlayoutManager
        root.RvPics.adapter = animeAdapter

        loaddatafromAPI(startpage,filter!! )

        animeAdapter.Myonclicklistener = object : onclicklistener {
            override fun detailsonClick(animeitem: DataPopular) {
                Log.v("TAG2", "this is click sar")

               var malId=animeitem.malId
                Log.d("id","value of $malId")

                val intent=  Intent(context,AnimeDetailsActivity::class.java)
                intent.putExtra("malId",malId)
                startActivity(intent)

            }
        }





        root.RvPics.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                var currentItems = GlayoutManager.childCount
                var totalItems = GlayoutManager.itemCount
                var scrollOutItems = GlayoutManager.findFirstVisibleItemPosition()
                Log.v("ITEMS", "$currentItems, $totalItems, $scrollOutItems")

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false
                    startpage++
                    Log.i("scroll", "page value is $startpage")
//                    root.LoadingCircle.visibility=View.VISIBLE

                    loaddatafromAPI(startpage,filter)
//                    root.LoadingCircle.visibility=View.INVISIBLE

//
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                    Log.d("TAG", "scrolling is $isScrolling")
                }
            }
        })





        return root
    }

    fun loaddatafromAPI(page: Int,type: String) {
//        val loading = view!!.findViewById<ProgressBar>(R.id.LoadingCircle)
//       loading.visibility=View.VISIBLE

        val pApi = RetrofitClient.AnimeAPI


        pApi.getPICS(page,type).enqueue(object : Callback<DiscoverAnime> {
            override fun onFailure(call: Call<DiscoverAnime>, t: Throwable) {

                Toast.makeText(context,"Try Again No Network Available",Toast.LENGTH_LONG).show()


            }

            override fun onResponse(call: Call<DiscoverAnime>, response: Response<DiscoverAnime>) {
                val r = response.body()?.datapopular
                r?.let {
                    dataPopular.addAll(it)
                    animeAdapter.notifyDataSetChanged()
                }
//                loading.visibility=View.VISIBLE


            }
        })
    }
//


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int,type:String): Popular {
            return Popular().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putString("type",type)
                }
            }
        }
    }


}