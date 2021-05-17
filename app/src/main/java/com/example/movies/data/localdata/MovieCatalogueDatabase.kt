package com.example.movies.data.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.data.model.Movie
import com.example.movies.data.model.TvShow

@Database(entities = [Movie::class, TvShow::class], version = 1)
abstract class MovieCatalogueDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
    abstract fun tvShowDao() : TvShowDao
}