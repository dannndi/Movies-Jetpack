package com.example.movies.data.repo

import com.example.movies.data.api.TmdbApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TmdbRepoTest {

    private lateinit var repository: FakeTmdbRepo

    @Before
    fun setUp() {
        val tmdbApi = Retrofit.Builder()
            .baseUrl(TmdbApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApi::class.java)

        repository = FakeTmdbRepo(tmdbApi)
    }

    @Test
    fun repoGetMovie() = runBlocking {
        val movie = repository.getNowPlayingMoviesFromServer()
        Assert.assertNotNull(movie)
    }

    @Test
    fun repoGetTvShow() = runBlocking {
        val tvShow = repository.getOnAirTvShowFromServer()
        Assert.assertNotNull(tvShow)
    }

    @Test
    fun repoGetMovieDetail() = runBlocking {
        val movie = repository.getDetailMovie(399566)
        Assert.assertNotNull(movie)
    }

    @Test
    fun repoGetTvShowDetail() = runBlocking {
        val tvShow = repository.getDetailTvShow(88396)
        Assert.assertNotNull(tvShow)
    }

    @Test
    fun repoGetMovieCast() = runBlocking {
        val movieCast = repository.getMovieCast(399566)
        Assert.assertNotNull(movieCast)
    }

    @Test
    fun repoGetTvShowCast() = runBlocking {
        val tvShowCast = repository.getTvShowCast(88396)
        Assert.assertNotNull(tvShowCast)
    }
}