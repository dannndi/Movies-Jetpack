package com.example.movies.ui.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.example.movies.data.repo.LocalDatabaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFavoriteViewModel @Inject constructor(
    private val localDatabaseRepo: LocalDatabaseRepo
) : ViewModel() {

    fun getFavoriteMovies() = LivePagedListBuilder(
        localDatabaseRepo.getFavoriteMovies(),
        20
    ).build()
}