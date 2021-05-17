package com.example.movies.data.repo

import com.example.movies.data.localdata.MovieCatalogueDatabase
import com.example.movies.data.localdata.MovieDao
import com.example.movies.data.localdata.TvShowDao
import com.example.movies.data.model.Movie
import com.example.movies.data.model.TvShow

class FakeLocalDatabaseRepo(movieCatalogueDatabase: MovieCatalogueDatabase) {
    private val movieDao: MovieDao = movieCatalogueDatabase.movieDao()
    private val tvShowDao: TvShowDao = movieCatalogueDatabase.tvShowDao()

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    suspend fun addFavoriteMovie(movie: Movie) = movieDao.addFavorite(movie)

    suspend fun deleteFavoriteMovie(movie: Movie) = movieDao.deleteFavorite(movie)

    suspend fun checkIsFavoriteMovie(movie: Movie) = movieDao.isFavorite(movie.id)

    fun getFavoriteTvShow() = tvShowDao.getFavoriteTvShow()

    suspend fun addFavoriteTvShow(tvShow: TvShow) = tvShowDao.addFavorite(tvShow)

    suspend fun deleteFavoriteTvShow(tvShow: TvShow) = tvShowDao.deleteFavorite(tvShow)

    suspend fun checkIsFavoriteTvShow(tvShow: TvShow) = tvShowDao.isFavorite(tvShow.id)
}