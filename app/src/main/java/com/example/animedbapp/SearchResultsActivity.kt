package com.example.animedbapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.example.animedbapp.Adapters.SearchResultAdapter
import com.example.animedbapp.Modals.SearchResults
import com.example.animedbapp.Networking.RetrofitClient
import kotlinx.android.synthetic.main.activity_search_results.*
import kotlinx.android.synthetic.main.activity_search_results.toolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SearchResultsActivity : AppCompatActivity(),CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    val searchResultsList=ArrayList<SearchResults>()
    val searchResultAdapter=SearchResultAdapter(searchResultsList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)
        val query=intent.getStringExtra("searchResults")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        RvSearchResults.layoutManager=LinearLayoutManager(this@SearchResultsActivity)
        RvSearchResults.adapter=searchResultAdapter


        launch {
            val animeService= RetrofitClient.AnimeAPI
            val searchresponse=animeService.getSearchResults(query)
            if(searchresponse.body()?.resultsList!!.isNotEmpty()){
                Log.d("Sea","this is searched")
                val searchResults=searchresponse.body()?.resultsList
                if (searchResults != null) {
                    searchResultsList.addAll(searchResults)
                    searchResultAdapter.notifyDataSetChanged()
                }




            }
            else {
                Toast.makeText(this@SearchResultsActivity,"Please try Again",Toast.LENGTH_SHORT).show()
            }
        }
        searchResultAdapter.Myonclicklistener=object :onSearchResultsListener{
            override fun SearchResultsDetailsonClick(animeitem: SearchResults) {
                val malId=animeitem.malId
                val intent = Intent(this@SearchResultsActivity, AnimeDetailsActivity::class.java)
                intent.putExtra("malId", malId)
                startActivity(intent)
            }

        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView



        searchView .setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                OnSearchResponse(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                    Log.d("Search","value is  $newText")
                return true

            }

        })
        searchView.setOnQueryTextFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                searchView.showKeyboard()
            }
            else {
                searchView.hideKeyboard()
            }
        }

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_search  -> {
                TransitionManager.beginDelayedTransition(this.findViewById(R.id.toolbar) as ViewGroup)
                MenuItemCompat.expandActionView(item)
                return true


            }
            android.R.id.home ->{
                onBackPressed()
                return true
            }



        }
        return super.onOptionsItemSelected(item)
    }
    private fun OnSearchResponse(query: String) {
        val intent= Intent(this@SearchResultsActivity,SearchResultsActivity::class.java)
        intent.putExtra("searchResults",query)
        startActivity(intent)


    }
    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(getWindowToken(), 0)
    }
}
