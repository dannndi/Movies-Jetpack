package com.example.movies.data.repo

import com.example.movies.data.api.TmdbApi
import com.example.movies.data.model.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class FakeTmdbRepo(private val tmdbApi: TmdbApi) {
    suspend fun getNowPlayingMoviesFromServer(): List<Movie> {
        return tmdbApi.getNowPlayingMovie().results
    }

    suspend fun getOnAirTvShowFromServer(): List<TvShow> {
        return tmdbApi.getOnAirTvShow().results
    }

    suspend fun getDetailMovie(id: Int): MovieDetail {
        return tmdbApi.getMovieDetail(id)
    }

    suspend fun getDetailTvShow(id: Int): TvShowDetail {
        return tmdbApi.getTvShowDetail(id)
    }

    suspend fun getMovieCast(id: Int): List<Cast> {
        return tmdbApi.getMovieCredits(id).cast
    }

    suspend fun getTvShowCast(id: Int): List<Cast> {
        return tmdbApi.getTvShowCredits(id).cast
    }
}
