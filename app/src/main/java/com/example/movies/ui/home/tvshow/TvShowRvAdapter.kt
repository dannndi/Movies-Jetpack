package com.example.movies.ui.home.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.Movie
import com.example.movies.data.model.TvShow
import com.example.movies.databinding.MovieTvItemBinding

class TvShowRvAdapter : RecyclerView.Adapter<TvShowRvAdapter.ViewHolder>() {

    private val tvShowList = ArrayList<TvShow>()
    private lateinit var onClick: (position: Int) -> Unit

    fun setList(tvShows: ArrayList<TvShow>) {
        tvShowList.clear()
        tvShowList.addAll(tvShows)
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
        holder.bind(tvShowList[position])
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount(): Int = tvShowList.size

    class ViewHolder(private val binding: MovieTvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tvShow: TvShow) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w154${tvShow.posterPath}")
                    .error(R.drawable.ic_broken_image)
                    .into(ivPoster)
                tvTitle.text = tvShow.originalName
                tvRating.text = tvShow.voteAverage.toString()
                tvYear.text = tvShow.firstAirDate.split("-")[0]
            }
        }
    }
}