package com.example.animedbapp

import com.example.animedbapp.Modals.AnimeDetails.Adaptation
import com.example.animedbapp.Modals.AnimeDetails.CharactersDetails
import com.example.animedbapp.Modals.AnimeDetails.RecommendationsDetails
import com.example.animedbapp.Modals.DataPopular
import com.example.animedbapp.Modals.SearchResults

interface onclicklistener{
    fun detailsonClick(animeitem: DataPopular)
}
interface onRelatedclickListener{
    fun RelateddetailsonClick(animeitem: Adaptation)
}
interface onRecomdclickListener{
    fun  RecomdDeatilsonClick(animeitem: RecommendationsDetails)
}
 interface  onSearchResultsListener {
     fun SearchResultsDetailsonClick(animeitem: SearchResults)
 }
interface  onBookmarksResultsListener{
    fun BookmarksResultsDeatilsonClick(animeitemId: Int)
}
interface  onCharactersclickListeners{
    fun  CharactersPageopenonClick(animeitem: CharactersDetails)
}
