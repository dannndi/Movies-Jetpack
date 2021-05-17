package com.example.movies.data.api

import android.os.Parcelable
import com.example.movies.data.model.Cast
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseCast(

	@field:SerializedName("cast")
	val cast: List<Cast>
) : Parcelable
