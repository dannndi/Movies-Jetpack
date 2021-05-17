package com.example.movies.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tvShow")
@Parcelize
data class TvShow(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @field:SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "first_air_date")
    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @ColumnInfo(name = "overview")
    @field:SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    @field:SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name = "original_name")
    @field:SerializedName("original_name")
    val originalName: String,

    @ColumnInfo(name = "vote_average")
    @field:SerializedName("vote_average")
    val voteAverage: Double,

) : Parcelable