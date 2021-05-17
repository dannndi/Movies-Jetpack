package com.example.movies.ui.detailtvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.TvShowDetail
import com.example.movies.databinding.SeasonItemBinding

class SeasonAdapter : RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    private val seasons = ArrayList<TvShowDetail.Seasons>()

    fun setList(seasonList: ArrayList<TvShowDetail.Seasons>) {
        seasons.clear()
        seasons.addAll(seasonList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SeasonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(seasons[position])
    }

    override fun getItemCount(): Int = seasons.size

    class ViewHolder(private val binding: SeasonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(season: TvShowDetail.Seasons) {
            binding.apply {
                if (season.posterPath != null) {
                    Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w154${season.posterPath}")
                        .error(R.drawable.ic_broken_image)
                        .into(ivPoster)
                }
                tvSeason.text = season.name
                tvEpisodes.text = "${season.episodeCount} Episodes"
                if(season.airDate != null){
                    tvYear.text = season.airDate.split("-")[0]
                }else{
                    tvYear.text = "-"
                }
                tvOverview.text = season.overview
            }
        }
    }
}