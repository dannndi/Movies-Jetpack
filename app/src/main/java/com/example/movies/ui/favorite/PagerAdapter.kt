package com.example.movies.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movies.ui.favorite.movie.MovieFavoriteFragment
import com.example.movies.ui.favorite.tvshow.TvShowFavoriteFragment

class PagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFavoriteFragment()
            1 -> fragment = TvShowFavoriteFragment()
        }
        return fragment as Fragment
    }
}