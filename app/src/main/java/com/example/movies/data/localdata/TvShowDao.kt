package com.example.movies.data.localdata

import androidx.paging.DataSource
import androidx.room.*
import com.example.movies.data.model.TvShow

@Dao
interface TvShowDao {

    @Query("SELECT * from tvShow")
    fun getFavoriteTvShow(): DataSource.Factory<Int, TvShow>

    @Query("SELECT count(*) from tvShow WHERE tvShow.id =:id")
    suspend fun isFavorite(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(tvShow: TvShow)

    @Delete
    suspend fun deleteFavorite(tvShow: TvShow)
}