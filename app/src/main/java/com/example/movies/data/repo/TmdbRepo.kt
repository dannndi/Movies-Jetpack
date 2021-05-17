package com.example.movies.data.repo

import com.example.movies.EspressoIdlingResource
import com.example.movies.data.api.TmdbApi
import com.example.movies.data.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbRepo @Inject constructor(private val tmdbApi: TmdbApi) {

    suspend fun getNowPlayingMoviesFromServer(): List<Movie> {
        EspressoIdlingResource.increment()
        val movies = tmdbApi.getNowPlayingMovie().results
        EspressoIdlingResource.decrement()
        return movies
    }

    suspend fun getOnAirTvShowFromServer(): List<TvShow> {
        EspressoIdlingResource.increment()
        val tvShow = tmdbApi.getOnAirTvShow().results
        EspressoIdlingResource.decrement()
        return tvShow
    }

    suspend fun getDetailMovie(id: Int): MovieDetail {
        EspressoIdlingResource.increment()
        val detailMovie = tmdbApi.getMovieDetail(id)
        EspressoIdlingResource.decrement()
        return detailMovie
    }

    suspend fun getDetailTvShow(id: Int): TvShowDetail {
        EspressoIdlingResource.increment()
        val detailTvShow = tmdbApi.getTvShowDetail(id)
        EspressoIdlingResource.decrement()
        return detailTvShow
    }

    suspend fun getMovieCast(id: Int): List<Cast> {
        EspressoIdlingResource.increment()
        val movieCast = tmdbApi.getMovieCredits(id).cast
        EspressoIdlingResource.decrement()
        return movieCast
    }

    suspend fun getTvShowCast(id: Int): List<Cast> {
        EspressoIdlingResource.increment()
        val tvShowCast = tmdbApi.getTvShowCredits(id).cast
        EspressoIdlingResource.decrement()
        return tvShowCast
    }
}