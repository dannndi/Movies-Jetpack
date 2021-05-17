package com.example.movies.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.example.movies.data.repo.LocalDatabaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowFavoriteViewModel @Inject constructor(
    private val localDatabaseRepo: LocalDatabaseRepo
) : ViewModel() {

    fun getFavoriteTvShow() = LivePagedListBuilder(
        localDatabaseRepo.getFavoriteTvShow(),
        20
    ).build()
}