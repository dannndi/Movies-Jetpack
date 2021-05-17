package com.example.movies.ui.detailtvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.model.Cast
import com.example.movies.data.model.TvShow
import com.example.movies.data.model.TvShowDetail
import com.example.movies.data.repo.LocalDatabaseRepo
import com.example.movies.data.repo.TmdbRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTvShowViewModel @Inject constructor(
    private val repository: TmdbRepo,
    private val localDatabaseRepo: LocalDatabaseRepo
) : ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>(false)
    val isFavorite get() = _isFavorite

    private val _tvShow = MutableLiveData<TvShowDetail>()
    val tvShow get() = _tvShow

    private val _castList = MutableLiveData<List<Cast>>()
    val castList get() = _castList

    fun getTvShow(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _tvShow.postValue(repository.getDetailTvShow(id))
        }
    }

    fun getCastList(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _castList.postValue(repository.getTvShowCast(id))
        }
    }

    fun checkIsFavorite(tvShow: TvShow) {
        viewModelScope.launch(Dispatchers.IO) {
            val result : Int? = localDatabaseRepo.checkIsFavoriteTvShow(tvShow)
            if (result != null) {
                _isFavorite.postValue(result > 0)
            }
        }
    }

    fun addToFavoriteTvShow(tvShow: TvShow) {
        viewModelScope.launch(Dispatchers.IO) {
            localDatabaseRepo.addFavoriteTvShow(tvShow)
        }
    }

    fun deleteFromFavoriteTvShow(tvShow: TvShow) {
        viewModelScope.launch(Dispatchers.IO) {
            localDatabaseRepo.deleteFavoriteTvShow(tvShow)
        }
    }
}