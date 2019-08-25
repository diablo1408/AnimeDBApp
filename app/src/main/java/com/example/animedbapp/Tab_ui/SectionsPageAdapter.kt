package com.example.animedbapp.Tab_ui

import android.content.Context

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.example.animedbapp.Fragments.Popular
import com.example.animedbapp.R


private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    R.string.tab_text_3
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

       when(position){
             0 -> return Popular.newInstance(position,"bypopularity")
             1 -> return Popular.newInstance(position,"favorite")
             2  -> return Popular.newInstance(position,"airing")

          else -> return Popular.newInstance(position,"bypopularity")
      }


        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}