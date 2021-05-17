package com.example.movies.data.api

import com.example.movies.data.model.TvShow
import com.google.gson.annotations.SerializedName

data class ResponseTvShow(

	@field:SerializedName("results")
	val results: List<TvShow>
)
