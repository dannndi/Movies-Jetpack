package com.example.movies.ui.detailmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.data.model.Cast
import com.example.movies.databinding.CastItemBinding

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {

    private val cast = ArrayList<Cast>()

    fun setList(castList: ArrayList<Cast>) {
        cast.clear()
        cast.addAll(castList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cast[position])
    }

    override fun getItemCount(): Int = cast.size

    class ViewHolder(private val binding: CastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cast: Cast) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w154${cast.profilePath}")
                    .error(R.drawable.ic_broken_image)
                    .into(civCast)
                tvCastName.text = cast.name
                tvCharacterName.text = cast.character
            }
        }
    }
}