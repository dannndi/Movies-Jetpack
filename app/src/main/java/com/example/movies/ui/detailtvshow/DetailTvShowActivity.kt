package com.example.movies.ui.detailtvshow

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.Movie
import com.example.movies.data.model.TvShow
import com.example.movies.databinding.ActivityDetailTvShowBinding
import com.example.movies.ui.detailmovie.CastAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private val viewModel: DetailTvShowViewModel by viewModels()
    private lateinit var castAdapter: CastAdapter
    private lateinit var seasonAdapter: SeasonAdapter
    private lateinit var tvShow : TvShow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_show)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Movie"

        val id = intent.getIntExtra("ID", 0)
        tvShow = intent.getParcelableExtra<TvShow>("TVSHOW") as TvShow

        showLoading(true)
        viewModel.getTvShow(id)
        viewModel.getCastList(id)
        viewModel.checkIsFavorite(tvShow)

        castAdapter = CastAdapter()
        binding.rvCast.apply {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(
                this@DetailTvShowActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }

        seasonAdapter = SeasonAdapter()
        binding.rvSeason.apply {
            adapter = seasonAdapter
            layoutManager = LinearLayoutManager(
                this@DetailTvShowActivity,
            )
            setHasFixedSize(true)
        }

        viewModel.tvShow.observe(this) { tvShow ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${tvShow.backdropPath}")
                .error(R.drawable.ic_broken_image)
                .into(binding.ivBackdrop)
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w154${tvShow.posterPath}")
                .error(R.drawable.ic_broken_image)
                .into(binding.ivPoster)
            binding.tvTitle.text = tvShow.originalName
            binding.tvYear.text = tvShow.firstAirDate.split("-")[0]
            if (tvShow.episodeRunTime.size > 0) {
                binding.tvDuration.text = "${tvShow.episodeRunTime[0]}m / Episode"
            } else {
                binding.tvDuration.text = "- / Episode"
            }
            binding.tvRating.text = tvShow.voteAverage.toString()
            binding.tvLanguange.text = tvShow.spokenLanguages[0].name
            binding.tvGenres.text = tvShow.genres.joinToString(",") { it.name }
            binding.tvOverview.text = tvShow.overview
            seasonAdapter.setList(ArrayList(tvShow.seasons))
            showLoading(false)
        }

        viewModel.castList.observe(this) { castList ->
            castAdapter.setList(ArrayList(castList))
        }

        viewModel.isFavorite.observe(this) { state ->
            binding.btnFavorite.isChecked = state
        }

        binding.btnFavorite.setOnClickListener {
            if (binding.btnFavorite.isChecked) {
                viewModel.addToFavoriteTvShow(tvShow)
            } else {
                viewModel.deleteFromFavoriteTvShow(tvShow)
            }
        }
    }

    private fun showLoading(boolean: Boolean) {
        if (boolean) {
            binding.ivPoster.visibility = View.GONE
            binding.tvTitle.visibility = View.GONE
            binding.tvYear.visibility = View.GONE
            binding.tvDuration.visibility = View.GONE
            binding.tvRating.visibility = View.GONE
            binding.tvLanguange.visibility = View.GONE
            binding.cast.visibility = View.GONE
            binding.rvCast.visibility = View.GONE
            binding.genres.visibility = View.GONE
            binding.tvGenres.visibility = View.GONE
            binding.overview.visibility = View.GONE
            binding.tvOverview.visibility = View.GONE
            binding.season.visibility = View.GONE
            binding.btnFavorite.visibility = View.GONE


            binding.loading.visibility = View.VISIBLE
        } else {
            binding.ivPoster.visibility = View.VISIBLE
            binding.tvTitle.visibility = View.VISIBLE
            binding.tvYear.visibility = View.VISIBLE
            binding.tvDuration.visibility = View.VISIBLE
            binding.tvRating.visibility = View.VISIBLE
            binding.tvLanguange.visibility = View.VISIBLE
            binding.cast.visibility = View.VISIBLE
            binding.rvCast.visibility = View.VISIBLE
            binding.genres.visibility = View.VISIBLE
            binding.tvGenres.visibility = View.VISIBLE
            binding.overview.visibility = View.VISIBLE
            binding.tvOverview.visibility = View.VISIBLE
            binding.season.visibility = View.VISIBLE
            binding.btnFavorite.visibility = View.VISIBLE

            binding.loading.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}