package com.example.movies.ui.detailmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.movies.data.model.Cast
import com.example.movies.data.model.Movie
import com.example.movies.data.model.MovieDetail
import com.example.movies.data.repo.LocalDatabaseRepo
import com.example.movies.data.repo.TmdbRepo
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private lateinit var dummyMovie : Movie

    private lateinit var viewModel: DetailMovieViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: TmdbRepo

    @Mock
    private lateinit var localRepo: LocalDatabaseRepo

    @Mock
    private lateinit var observer: Observer<MovieDetail>

    @Mock
    private lateinit var observerCast: Observer<List<Cast>>

    @Mock
    private lateinit var observerFavorite: Observer<Boolean>

    @Before
    fun setUp() {
        dummyMovie = Movie(
            1,
            "Dummy Overview",
            "Dummy Title",
            "2020-01-01",
            8.7,
            true,
            "Dummy Poster Path"
        )
        viewModel = DetailMovieViewModel(repository, localRepo)
    }

    @Test
    fun getDetailMovie() {
        runBlocking {
            viewModel.getMovie(399566)
            viewModel.movie.observeForever(observer)
            val movie = viewModel.movie.value
            verify(repository).getDetailMovie(399566)
            verify(observer).onChanged(movie)
        }
    }

    @Test
    fun getDetailMovieCast() {
        runBlocking {
            viewModel.getCastList(399566)
            viewModel.castList.observeForever(observerCast)
            val cast = viewModel.castList.value
            verify(repository).getMovieCast(399566)
            verify(observerCast).onChanged(cast)
        }
    }

    @Test
    fun addFavoriteMovie() = runBlocking {
        viewModel.addToFavoriteMovie(dummyMovie)
        viewModel.checkIsFavorite(dummyMovie)
        viewModel.isFavorite.observeForever(observerFavorite)
        val value = viewModel.isFavorite.value
        verify(localRepo).addFavoriteMovie(dummyMovie)
        verify(localRepo).checkIsFavoriteMovie(dummyMovie)
        verify(observerFavorite).onChanged(value)
    }

    @Test
    fun deleteFavoriteMovie() = runBlocking {
        viewModel.addToFavoriteMovie(dummyMovie)
        viewModel.deleteFromFavoriteMovie(dummyMovie)
        viewModel.checkIsFavorite(dummyMovie)
        viewModel.isFavorite.observeForever(observerFavorite)
        val value = viewModel.isFavorite.value
        verify(localRepo).addFavoriteMovie(dummyMovie)
        verify(localRepo).deleteFavoriteMovie(dummyMovie)
        verify(localRepo).checkIsFavoriteMovie(dummyMovie)
        verify(observerFavorite).onChanged(value)
    }
}