package com.example.movies.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R
import com.example.movies.databinding.ActivityFavoriteBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Movies)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Favorite Movie Catalogue"
        supportActionBar?.elevation = 0F
        binding.viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.homeTabs, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Movie"
                1 -> tab.text = "Tv Show"
            }
        }.attach()
    }
}