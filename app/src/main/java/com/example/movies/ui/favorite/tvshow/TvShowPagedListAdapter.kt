package com.example.movies.ui.favorite.tvshow

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
import com.example.movies.data.model.TvShow
import com.example.movies.databinding.MovieTvItemBinding
import com.example.movies.ui.detailmovie.DetailMovieActivity
import com.example.movies.ui.detailtvshow.DetailTvShowActivity

class TvShowPagedListAdapter(private val activity: Activity) :
    PagedListAdapter<TvShow, TvShowPagedListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TvShow> =
            object : DiffUtil.ItemCallback<TvShow>() {
                override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieTvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position) as TvShow
        holder.bind(tvShow)
        holder.itemView.setOnClickListener {
            Intent(activity, DetailTvShowActivity::class.java).apply {
                putExtra("ID", tvShow.id)
                putExtra("TVSHOW", tvShow)
                activity.startActivity(this)
            }
        }
    }

    inner class ViewHolder(private val binding: MovieTvItemBinding) :
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