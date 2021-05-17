package com.example.movies.ui.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.TestCoroutineRule
import com.example.movies.data.model.TvShow
import com.example.movies.data.repo.TmdbRepo
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var repository: TmdbRepo

    @Mock
    private lateinit var observer: Observer<List<TvShow>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(repository)
        viewModel.getTvShowList()
    }

    @Test
    fun getTvShow() {
        testCoroutineRule.runBlockingTest {
            viewModel.tvShowList.observeForever(observer)
            val tvShow = viewModel.tvShowList.value
            verify(repository).getOnAirTvShowFromServer()
            verify(observer).onChanged(tvShow)
        }
    }
}