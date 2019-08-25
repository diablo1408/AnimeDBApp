package com.example.animedbapp

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.animedbapp.Tab_ui.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.view.MenuItemCompat

import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.transition.TransitionManager


class MainActivity : AppCompatActivity() {

//    private lateinit var auth: FirebaseAuth
    // [END declare_auth]


    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

            R.id.navigation_discover -> {

                val sectionsPagerAdapter =
                    SectionsPagerAdapter(this, supportFragmentManager)
                val viewPager: ViewPager = findViewById(R.id.view_pager)
                viewPager.adapter = sectionsPagerAdapter
                val tabs: TabLayout = findViewById(R.id.tabs)
                tabs.setupWithViewPager(viewPager)
//                textMessage.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_seasons -> {

//                textMessage.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_bookmarks ->{
               startActivity(Intent(this,BookmarksActivity::class.java))
                return@OnNavigationItemSelectedListener false
            }
            R.id.navigation_account ->{
                startActivity(Intent(this,AccountLoginActivity::class.java))
                return@OnNavigationItemSelectedListener false
            }


        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.selectedItemId=R.id.navigation_discover







    }


        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.search_menu, menu)
            val searchItem = menu.findItem(R.id.action_search)
           val searchView = searchItem.actionView as SearchView
            searchView.setBackgroundColor(Color.WHITE)



               searchView .setOnQueryTextListener(object :SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        if (query.length >= 3)
                            OnSearchResponse(query)
                        else {
                            Toast.makeText(this@MainActivity, "Minimum length is 3 Plz try again", Toast.LENGTH_SHORT).show()
                        }
                        return true
                    }
                    else{
                        Toast.makeText(this@MainActivity, "Nothing to Search", Toast.LENGTH_SHORT).show()
                        return true
                    }
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
            searchItem.setOnActionExpandListener(object: MenuItem.OnActionExpandListener {
                override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                    searchView.isIconified = false
                    searchView.requestFocusFromTouch()
                    return true
                }

                override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                    // when back, clear all search
                    searchView.setQuery("", true)
                    return true
                }
            })

             return true

        }

    private fun OnSearchResponse(query: String) {
        val intent= Intent(this@MainActivity,SearchResultsActivity::class.java)
        intent.putExtra("searchResults",query)
        startActivity(intent)


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
         when(item?.itemId){
             R.id.action_search  -> {
                 TransitionManager.beginDelayedTransition(this.findViewById(R.id.toolbar) as ViewGroup)
                 MenuItemCompat.expandActionView(item)
                 return true


             }



         }
        return super.onOptionsItemSelected(item)
    }
    fun View.showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(getWindowToken(), 0)
    }
//



}


