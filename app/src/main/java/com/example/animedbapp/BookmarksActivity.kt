package com.example.animedbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animedbapp.Adapters.BookmarkAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_bookmarks.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext


class BookmarksActivity : AppCompatActivity(),CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    val idList = ArrayList<Int>()

    val bookmarkAdapter = BookmarkAdapter(idList)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmarks)
        setSupportActionBar(toolbarBookmarks)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        RvmyList.layoutManager = LinearLayoutManager(this)
        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser!=null&&currentUser.isEmailVerified){
            LoadBookmarksTitlesFromDatabase()
        }
        else{
            Toast.makeText(this@BookmarksActivity,"No Account Found",Toast.LENGTH_SHORT).show()
            onBackPressed()
        }


        Log.d("data","value of list is $idList")
        RvmyList.adapter = bookmarkAdapter
         bookmarkAdapter.Myonclicklistener=object :onBookmarksResultsListener{
             override fun BookmarksResultsDeatilsonClick(animeitemId: Int) {
                 val intent = Intent(this@BookmarksActivity, AnimeDetailsActivity::class.java)
                 intent.putExtra("malId", animeitemId)
                 startActivity(intent)
             }

         }

    }

    private fun LoadBookmarksTitlesFromDatabase() {
        val ref = FirebaseDatabase.getInstance().reference.child("MyWatchList")
            .child(FirebaseAuth.getInstance().uid!!)

            ref.orderByKey().addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(idSnapshot: DataSnapshot) {
                    val snapshotIterator = idSnapshot.children
                    val iterator = snapshotIterator.iterator()
                    while (iterator.hasNext()) {
                        val next = iterator.next() as DataSnapshot
                        Log.i("db", "Value = " + next.child("id").value!!)

                        idList.add(next.child("id").value!!.toString().toInt())
                        Log.i("db", "Value = $idList")
                    }
                    Log.d("data1","value of list is $idList")
                    bookmarkAdapter.notifyDataSetChanged()
                }
            })




    }
}
