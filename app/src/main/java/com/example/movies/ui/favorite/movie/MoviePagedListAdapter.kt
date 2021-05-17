package com.example.movies.ui.favorite.movie

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.Movie
import com.example.movies.databinding.MovieTvItemBinding
import com.example.movies.ui.detailmovie.DetailMovieActivity

class MoviePagedListAdapter(private val activity: Activity) :
    PagedListAdapter<Movie, MoviePagedListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieTvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position) as Movie
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            Intent(activity, DetailMovieActivity::class.java).apply {
                putExtra("ID", movie.id)
                putExtra("MOVIE", movie)
                activity.startActivity(this)
            }
        }
    }


    inner class ViewHolder(private val binding: MovieTvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w154${movie.posterPath}")
                    .error(R.drawable.ic_broken_image)
                    .into(ivPoster)
                tvTitle.text = movie.originalTitle
                tvRating.text = movie.voteAverage.toString()
                tvYear.text = movie.releaseDate.split("-")[0]
            }
        }
    }
}