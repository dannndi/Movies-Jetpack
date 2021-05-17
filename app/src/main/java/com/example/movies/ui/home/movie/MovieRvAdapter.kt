package com.example.movies.ui.home.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.Movie
import com.example.movies.databinding.MovieTvItemBinding

class MovieRvAdapter : RecyclerView.Adapter<MovieRvAdapter.ViewHolder>() {

    private val movieList = ArrayList<Movie>()
    private lateinit var onClick: (position: Int) -> Unit

    fun setList(movies: ArrayList<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    fun setOnClickListener(click: (position: Int) -> Unit) {
        onClick = click
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieTvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int = movieList.size

    class ViewHolder(private val binding: MovieTvItemBinding) :
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