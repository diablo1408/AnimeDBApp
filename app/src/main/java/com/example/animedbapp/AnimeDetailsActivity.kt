package com.example.animedbapp


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem

import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.animedbapp.Adapters.CharacterAdapter
import com.example.animedbapp.Adapters.GenreAdapter
import com.example.animedbapp.Adapters.RecommendationAdapter
import com.example.animedbapp.Adapters.RelatedAdapter
import com.example.animedbapp.Modals.AnimeDetails.*

import com.example.animedbapp.Networking.RetrofitClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.animedetails_scrollable.*

import kotlinx.android.synthetic.main.layout_info.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_anime_details.*
import androidx.browser.customtabs.CustomTabsIntent




class AnimeDetailsActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    var relatedList = ArrayList<Adaptation>()
    var genreList = ArrayList<Genre>()
    var charactersList = ArrayList<CharactersDetails>()
    var recomsList = ArrayList<RecommendationsDetails>()
    val relatedAdapter = RelatedAdapter(relatedList)
    val genreAdapter = GenreAdapter(genreList)
    val characterAdapter = CharacterAdapter(charactersList)
    val recommendationAdapter = RecommendationAdapter(recomsList)
    var isFABOpen=false





    @SuppressLint("RestrictedApi")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_anime_details)
        setSupportActionBar(toolbarAnimedetail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        val Rlayoutmanager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        Rvrealted.layoutManager = Rlayoutmanager
        RvGenres.layoutManager = StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL)
        RvCharacters.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        RvRecoms.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false)
        RvGenres.setHasFixedSize(true)
        Rvrealted.adapter = relatedAdapter
        RvGenres.adapter = genreAdapter
        RvCharacters.adapter = characterAdapter
        RvRecoms.adapter = recommendationAdapter

        relatedAdapter.Myonclicklistener = object : onRelatedclickListener {
            override fun RelateddetailsonClick(animeitem: Adaptation) {
                var malId = animeitem.malId
                Log.d("id", "value of $malId")

                val intent = Intent(this@AnimeDetailsActivity, AnimeDetailsActivity::class.java)
                intent.putExtra("malId", malId)
                startActivity(intent)
            }

        }
        recommendationAdapter.Myonclicklistener=object :onRecomdclickListener{
            override fun RecomdDeatilsonClick(animeitem: RecommendationsDetails) {
                val malId=animeitem.malId
                val intent = Intent(this@AnimeDetailsActivity, AnimeDetailsActivity::class.java)
                intent.putExtra("malId", malId)
                startActivity(intent)

            }

        }
        characterAdapter.Myonclicklistener=object :onCharactersclickListeners{
            override fun CharactersPageopenonClick(animeitem: CharactersDetails) {
                val url=animeitem.url
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                builder.addDefaultShareMenuItem()
                builder.setStartAnimations(this@AnimeDetailsActivity, R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom)

                customTabsIntent.launchUrl(this@AnimeDetailsActivity, Uri.parse(url))
                builder.setExitAnimations(this@AnimeDetailsActivity, R.anim.abc_slide_in_top, R.anim.abc_slide_in_top)
            }

        }
        scrollableView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(scrollY>0){
                floating_action_button.hide()
                floating_action_buttonR.visibility=View.INVISIBLE
                floating_action_button2.visibility=View.INVISIBLE
                Log.d("scroll","this is hidden")
            }
            else {
                floating_action_button.show()
                floating_action_buttonR.visibility=View.VISIBLE
                floating_action_button2.visibility=View.VISIBLE
                Log.d("scroll2","this is show")
            }
        }
        floating_action_button.setOnClickListener {

            if(!isFABOpen){
                showFABMenu()
                Log.d("fab","fab clicked")
            }
            else{
                closeFABMenu()
            }

        }



        val malId = intent.getIntExtra("malId", 1)
        LoadAnimeDetails(malId)
        launch {
            updatebuttonStatus(malId)
        }


        readmoreTv.setOnClickListener {
            readmoreTv.visibility = View.GONE
            arrowUp.visibility = View.VISIBLE
            synopsisTv.maxLines = Integer.MAX_VALUE

        }
        arrowUp.setOnClickListener {
            readmoreTv.visibility = View.VISIBLE
            arrowUp.visibility = View.GONE
            synopsisTv.maxLines = 5

        }


        btnAddList.setOnClickListener {



            val currentUser = FirebaseAuth.getInstance().currentUser
            Log.d("status", "this is clicked")

            if (currentUser != null&&currentUser.isEmailVerified) {
                val ref = FirebaseDatabase.getInstance().reference.child("MyWatchList")
                    .child(FirebaseAuth.getInstance().uid!!)
                if (btnAddList.tag == R.drawable.ic_playlist_add_white_24dp) {

                    val key = ref.push().key
                    ref.child("$key/").child("id")
                        .setValue(malId.toString())
                    btnAddList.setImageResource(R.drawable.ic_check_white_24dp)
                    btnAddList.tag = R.drawable.ic_check_white_24dp
                    Log.d("db", "data is added")


                } else {
//
                    val idQuery = ref.orderByChild("id").equalTo(malId.toString())

                    idQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (idSnapshot in dataSnapshot.children) {
                                idSnapshot.ref.removeValue()
                                Log.d("db2", "value is $idSnapshot")
                            }

                            btnAddList.setImageResource(R.drawable.ic_playlist_add_white_24dp)
                            btnAddList.tag = R.drawable.ic_playlist_add_white_24dp
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            Log.e("TAG", "onCancelled", databaseError.toException())
                        }
                    })
                }
            }
            else{
                Toast.makeText(this@AnimeDetailsActivity,"Login to Bookmarked the Show",Toast.LENGTH_SHORT).show()
            }


        }
        floating_action_buttonR.setOnClickListener{
             val intent=Intent(this@AnimeDetailsActivity,ReviewsActivity::class.java)
            intent.putExtra("malId",malId)
            startActivity(intent)
        }


    }
    private fun showFABMenu(){
    isFABOpen=true

    floating_action_buttonR.animate().translationY(-resources.getDimension(R.dimen.standard_55))
    floating_action_button2.animate().translationY(-resources.getDimension(R.dimen.standard_105))
//    fab3.animate().translationY(-resources.getDimension(R.dimen.standard_155))
}

private fun closeFABMenu(){
    isFABOpen=false

    floating_action_buttonR.animate().translationY(0F)
    floating_action_button2.animate().translationY(0F)

//    fab3.animate().translationY(0)
}

    fun updatebuttonStatus(malId: Int) {



        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null&&currentUser.isEmailVerified) {
            val ref = FirebaseDatabase.getInstance().reference.child("MyWatchList")
                .child(FirebaseAuth.getInstance().uid!!)


            val idQuery = ref.orderByChild("id").equalTo(malId.toString())

            idQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (idSnapshot in dataSnapshot.children) {
//                    Log.d("db11","imside the loop ${dataSnapshot}")
//                 val Firebaseidclass=   dataSnapshot.children
                    val snapshotIterator = dataSnapshot.children
                    val iterator = snapshotIterator.iterator()
                    while (iterator.hasNext()) {
                        val next = iterator.next() as DataSnapshot
                        if (next.child("id").value.toString().toInt() == malId) {
                            btnAddList.setImageResource(R.drawable.ic_check_white_24dp)
                            btnAddList.tag = R.drawable.ic_check_white_24dp
                            Log.d("db21", "imside the if ${next.child("id").value}")
                            break
                        } else {
                            btnAddList.setImageResource(R.drawable.ic_playlist_add_white_24dp)
                            btnAddList.tag = R.drawable.ic_playlist_add_white_24dp
                            Log.d("db31", "imside the else ${next.child("id").value}")
                        }

                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("TAG", "onCancelled", databaseError.toException())
                    Toast.makeText(this@AnimeDetailsActivity,databaseError.toString(),Toast.LENGTH_SHORT).show()
                }
            })
        }
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

    fun LoadAnimeDetails(malId: Int) {
        launch {
            val pApi = RetrofitClient.AnimeAPI

            val response = pApi.getAnimeDetails(malId)
            val animeDetails = response.body()

            val characters = pApi.getCharactersPics(malId)

            val charactersList = characters.body()?.characters
            val recommendations = pApi.getRecommendations(malId)
            if (recommendations.body()?.recommendations!!.isNotEmpty()) {
                val recommendationsList = recommendations.body()?.recommendations
                SetAnimeDetailsUI(animeDetails!!, charactersList!!, recommendationsList!!)
            } else {
                SetAnimeDetailsUI(animeDetails!!, charactersList)
            }


        }

    }

    fun SetAnimeDetailsUI(
        animeDetails: AnimeDetails, charactersDetails: List<CharactersDetails>? = null,
        recommendationsDetails: List<RecommendationsDetails>? = null
    ) {
        if (animeDetails.studios!!.isNotEmpty()) {
            val studio = animeDetails.studios?.get(0)?.name
            studioTv.text = studio
        } else {
            val studio = "Unknown"
            studioTv.text = studio
        }
        val aired = animeDetails.aired?.airedIn
        var ytUrl = animeDetails.trailerUrl.toString()
        val genres = animeDetails.genres
        if (animeDetails.trailerUrl != null) {
            ytUrl = ytUrl.substringBefore("?").substring(30, 41)
            val youtubefragment = fragmentManager.findFragmentById(R.id.youtubeFragment) as? YouTubePlayerFragment
            youtubefragment?.initialize(MyApi.ytApi,
                object : YouTubePlayer.OnInitializedListener {
                    override fun onInitializationSuccess(
                        provider: YouTubePlayer.Provider,
                        youTubePlayer: YouTubePlayer, b: Boolean
                    ) {
                        if (!b) {
                            youTubePlayer.cueVideo(ytUrl)
                        }
                        // do any work here to cue video, play video, etc.

                    }

                    override fun onInitializationFailure(
                        provider: YouTubePlayer.Provider,
                        youTubeInitializationResult: YouTubeInitializationResult
                    ) {
                        Toast.makeText(
                            this@AnimeDetailsActivity,
                            youTubeInitializationResult as CharSequence,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                })

        }

//         Log.d("str","${ytUrl.length}")
          supportActionBar?.title=animeDetails.titleEnglish

        Picasso.get().load(animeDetails.imageUrl).into(animeimage)
        animeTitleTv.text = animeDetails.title
        ScoreTv.text = animeDetails.score.toString()
        rankTv.text = animeDetails.rank.toString()
        englishTitleTv.text = animeDetails.titleEnglish
        typeTv.text = animeDetails.type
        yearTv.text = animeDetails.premiered.toString().substringAfter(" ")
        statusTv.text = animeDetails.status
        epTv.text = animeDetails.episodes.toString()+"eps"
        epInTv.text = animeDetails.episodes.toString()
        sourceTv.text = animeDetails.source
        ratingTv.text = animeDetails.rating
        statusInTv.text = animeDetails.status
        durationTv.text = animeDetails.duration
        airedTv.text = aired
        synopsisTv.text = animeDetails.synopsis






        animeDetails.related?.let {
            if (it.sequel != null) {
                relatedList.addAll(it.sequel!!)
            }
            if (it.spinOff != null) {
                relatedList.addAll(it.spinOff!!)
            }
            if (it.other != null) {
                relatedList.addAll(it.other!!)
            }
            if (it.alternativeVersion != null) {
                relatedList.addAll(it.alternativeVersion!!)
            }
            if (it.sideStory != null) {
                relatedList.addAll(it.sideStory!!)
            }
            if (it.summary != null) {
                relatedList.addAll(it.summary!!)
            }


        }
        genreList.addAll(genres!!)
        if (charactersDetails != null) {
            charactersDetails.let { charactersList.addAll(it) }
        }
        if (recommendationsDetails != null) {
            recommendationsDetails.let { recomsList.addAll(it) }
        }
        if (recommendationsDetails == null) {
            CvRecs.visibility = View.INVISIBLE
        }
        if (relatedList.isNotEmpty()) {
            relatedAdapter.notifyDataSetChanged()
        } else {
            CvRelated.visibility = View.INVISIBLE
        }
        genreAdapter.notifyDataSetChanged()
        characterAdapter.notifyDataSetChanged()
        recommendationAdapter.notifyDataSetChanged()


    }


}
