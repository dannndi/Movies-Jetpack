package com.example.movies.data.localdata

import androidx.paging.DataSource
import androidx.room.*
import com.example.movies.data.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * from movie")
    fun getFavoriteMovies(): DataSource.Factory<Int, Movie>

    @Query("SELECT count(*) from movie WHERE movie.id =:id")
    suspend fun isFavorite(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(movie: Movie)

    @Delete
    suspend fun deleteFavorite(movie: Movie)
}