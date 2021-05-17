package com.example.movies.ui.home.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.model.Movie
import com.example.movies.data.repo.TmdbRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: TmdbRepo
) : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList get() = _movieList

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _movieList.postValue(repository.getNowPlayingMoviesFromServer())
        }
    }
}