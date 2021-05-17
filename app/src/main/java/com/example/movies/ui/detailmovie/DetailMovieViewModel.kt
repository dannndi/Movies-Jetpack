package com.example.movies.ui.detailmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.model.Cast
import com.example.movies.data.model.Movie
import com.example.movies.data.model.MovieDetail
import com.example.movies.data.repo.LocalDatabaseRepo
import com.example.movies.data.repo.TmdbRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: TmdbRepo,
    private val localDatabaseRepo: LocalDatabaseRepo
) : ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>(false)
    val isFavorite get() = _isFavorite

    private val _movie = MutableLiveData<MovieDetail>()
    val movie get() = _movie

    private val _castList = MutableLiveData<List<Cast>>()
    val castList get() = _castList

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val cast = repository.getDetailMovie(id)
            _movie.postValue(cast)
        }
    }

    fun getCastList(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _castList.postValue(repository.getMovieCast(id))
        }
    }

    fun checkIsFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            val result: Int? = localDatabaseRepo.checkIsFavoriteMovie(movie)
            if (result != null) {
                _isFavorite.postValue(result > 0)
            }
        }
    }

    fun addToFavoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            localDatabaseRepo.addFavoriteMovie(movie)
        }
    }

    fun deleteFromFavoriteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            localDatabaseRepo.deleteFavoriteMovie(movie)
        }
    }
}