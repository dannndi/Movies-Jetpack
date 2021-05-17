package com.example.movies.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowDetail(

	@field:SerializedName("backdrop_path")
	val backdropPath: String,

	@field:SerializedName("first_air_date")
	val firstAirDate: String,

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("spoken_languages")
	val spokenLanguages: List<SpokenLanguages>,

	@field:SerializedName("seasons")
	val seasons: List<Seasons>,

	@field:SerializedName("genres")
	val genres: List<Genres>,

	@field:SerializedName("original_name")
	val originalName: String,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("episode_run_time")
	val episodeRunTime: List<Int>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("last_air_date")
	val lastAirDate: String,

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
    data class Seasons(

		@field:SerializedName("air_date")
		val airDate: String,

		@field:SerializedName("overview")
		val overview: String,

		@field:SerializedName("episode_count")
		val episodeCount: Int,

		@field:SerializedName("name")
		val name: String,

		@field:SerializedName("season_number")
		val seasonNumber: Int,

		@field:SerializedName("id")
		val id: Int,

		@field:SerializedName("poster_path")
		val posterPath: String
	) : Parcelable

    @Parcelize
    data class SpokenLanguages(

		@field:SerializedName("name")
		val name: String
	) : Parcelable

}