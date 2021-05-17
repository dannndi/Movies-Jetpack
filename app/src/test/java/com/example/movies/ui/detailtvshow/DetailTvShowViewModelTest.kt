package com.example.movies.ui.detailtvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.data.model.Cast
import com.example.movies.data.model.TvShow
import com.example.movies.data.model.TvShowDetail
import com.example.movies.data.repo.LocalDatabaseRepo
import com.example.movies.data.repo.TmdbRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    private lateinit var dummyTvShow: TvShow

    private lateinit var viewModel: DetailTvShowViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TmdbRepo

    @Mock
    private lateinit var localRepo: LocalDatabaseRepo

    @Mock
    private lateinit var observer: Observer<TvShowDetail>

    @Mock
    private lateinit var observerCast: Observer<List<Cast>>

    @Mock
    private lateinit var observerFavorite: Observer<Boolean>

    @Before
    fun setUp() {
        dummyTvShow = TvShow(
            1,
            "2020-01-01",
            "Dummy Overview",
            "Dummy Poster Path",
            "Dummy Name",
            8.7,
        )
        viewModel = DetailTvShowViewModel(repository, localRepo)
    }

    @Test
    fun getDetailTvShow() {
        runBlocking {
            viewModel.getTvShow(88396)
            viewModel.tvShow.observeForever(observer)
            val tvShow = viewModel.tvShow.value
            verify(repository).getDetailTvShow(88396)
            verify(observer).onChanged(tvShow)
        }
    }

    @Test
    fun getDetailTvShowCast() {
        runBlocking {
            viewModel.getCastList(88396)
            viewModel.castList.observeForever(observerCast)
            val cast = viewModel.castList.value
            verify(repository).getTvShowCast(88396)
            verify(observerCast).onChanged(cast)
        }
    }

    @Test
    fun addFavoriteTvShow() = runBlocking {
        viewModel.addToFavoriteTvShow(dummyTvShow)
        viewModel.checkIsFavorite(dummyTvShow)
        viewModel.isFavorite.observeForever(observerFavorite)
        val value = viewModel.isFavorite.value
        verify(localRepo).addFavoriteTvShow(dummyTvShow)
        verify(localRepo).checkIsFavoriteTvShow(dummyTvShow)
        verify(observerFavorite).onChanged(value)
    }

    @Test
    fun deleteFavoriteTvShow() = runBlocking {
        viewModel.addToFavoriteTvShow(dummyTvShow)
        viewModel.deleteFromFavoriteTvShow(dummyTvShow)
        viewModel.checkIsFavorite(dummyTvShow)
        viewModel.isFavorite.observeForever(observerFavorite)
        val value = viewModel.isFavorite.value
        verify(localRepo).addFavoriteTvShow(dummyTvShow)
        verify(localRepo).deleteFavoriteTvShow(dummyTvShow)
        verify(localRepo).checkIsFavoriteTvShow(dummyTvShow)
        verify(observerFavorite).onChanged(value)
    }
}