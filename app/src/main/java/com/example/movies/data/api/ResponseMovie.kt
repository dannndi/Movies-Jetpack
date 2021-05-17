package com.example.movies.data.api

import com.example.movies.data.model.Movie
import com.google.gson.annotations.SerializedName

data class ResponseMovie(
	@field:SerializedName("results")
	val results: List<Movie>
)
