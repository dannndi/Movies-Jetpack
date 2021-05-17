package com.example.movies.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "original_title")
    @field:SerializedName("original_title")
    val originalTitle: String,

    @ColumnInfo(name = "release_date")
    @field:SerializedName("release_date")
    val releaseDate: String,

    @ColumnInfo(name = "vote_average")
    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "adult")
    @field:SerializedName("adult")
    val adult: Boolean,

    @ColumnInfo(name = "poster_path")
    @field:SerializedName("poster_path")
    val posterPath: String
) : Parcelable