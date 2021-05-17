package com.example.movies.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail(

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("spoken_languages")
	val spokenLanguages: List<SpokenLanguages>,

	@field:SerializedName("original_title")
	val originalTitle: String,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("genres")
	val genres: List<Genres>,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("runtime")
	val runtime: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("poster_path")
	val posterPath: String
) : Parcelable {

    @Parcelize
    data class Genres(

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("id")
		val id: Int
	) : Parcelable

    @Parcelize
    data class SpokenLanguages(

		@field:SerializedName("name")
		val name: String
	) : Parcelable
}
