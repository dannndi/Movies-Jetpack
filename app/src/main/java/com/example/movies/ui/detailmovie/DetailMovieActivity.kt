package com.example.movies.ui.detailmovie

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.Movie
import com.example.movies.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val viewModel: DetailMovieViewModel by viewModels()
    private lateinit var castAdapter: CastAdapter
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Detail Movie"

        val id = intent.getIntExtra("ID", 0)
        movie = intent.getParcelableExtra<Movie>("MOVIE") as Movie

        showLoading(true)
        viewModel.getMovie(id)
        viewModel.getCastList(id)
        viewModel.checkIsFavorite(movie)

        castAdapter = CastAdapter()
        binding.rvCast.apply {
            adapter = castAdapter
            layoutManager = LinearLayoutManager(
                this@DetailMovieActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            setHasFixedSize(true)
        }

        viewModel.movie.observe(this) { movie ->
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                .error(R.drawable.ic_broken_image)
                .into(binding.ivBackdrop)
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/w154${movie.posterPath}")
                .error(R.drawable.ic_broken_image)
                .into(binding.ivPoster)
            binding.tvTitle.text = movie.originalTitle
            binding.tvYear.text = movie.releaseDate.split("-")[0]
            binding.tvDuration.text = getTime(movie.runtime)
            binding.tvRating.text = movie.voteAverage.toString()
            binding.tvLanguange.text = movie.spokenLanguages[0].name
            binding.tvGenres.text = movie.genres.joinToString(",") { it.name }
            binding.tvOverview.text = movie.overview
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
                viewModel.addToFavoriteMovie(movie)
            } else {
                viewModel.deleteFromFavoriteMovie(movie)
            }
        }
    }

    private fun getTime(time: Int): String {
        val hour = time / 60
        val minute = time % 60
        return "${hour}h ${minute}m"
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