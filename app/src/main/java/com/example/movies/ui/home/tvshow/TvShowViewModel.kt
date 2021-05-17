package com.example.movies.ui.home.tvshow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.data.model.TvShow
import com.example.movies.data.repo.TmdbRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val repository: TmdbRepo
) : ViewModel() {

    private val _tvShowList = MutableLiveData<List<TvShow>>()
    val tvShowList get() = _tvShowList

    fun getTvShowList() {
        viewModelScope.launch(Dispatchers.IO) {
            _tvShowList.postValue(repository.getOnAirTvShowFromServer())
        }
    }

}