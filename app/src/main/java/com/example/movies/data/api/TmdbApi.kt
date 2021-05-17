package com.example.movies.data.api

import com.example.movies.BuildConfig
import com.example.movies.data.model.MovieDetail
import com.example.movies.data.model.TvShowDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.TMDB_API_KEY
    }

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("api_key") page: String = "1",
    ): ResponseMovie


    @GET("tv/on_the_air")
    suspend fun getOnAirTvShow(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US",
        @Query("api_key") page: String = "1",
    ): ResponseTvShow

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): MovieDetail

    @GET("movie/{id}/credits")
    suspend fun getMovieCredits(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): ResponseCast

    @GET("tv/{id}")
    suspend fun getTvShowDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): TvShowDetail

    @GET("tv/{id}/credits")
    suspend fun getTvShowCredits(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "en-US"
    ): ResponseCast

}