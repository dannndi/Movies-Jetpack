package com.example.movies.ui.favorite.movie

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.movies.data.localdata.MovieCatalogueDatabase
import com.example.movies.data.repo.LocalDatabaseRepo
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieFavoriteViewModelTest {
    private lateinit var viewModel: MovieFavoriteViewModel

    private lateinit var localDatabaseRepo: LocalDatabaseRepo

    private lateinit var movieCatalogueDatabase: MovieCatalogueDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context: Context = ApplicationProvider.getApplicationContext()
        movieCatalogueDatabase = Room.inMemoryDatabaseBuilder(
            context, MovieCatalogueDatabase::class.java
        ).build()

        localDatabaseRepo = LocalDatabaseRepo(movieCatalogueDatabase)
        viewModel = MovieFavoriteViewModel(localDatabaseRepo)
    }

    @After
    fun closing() {
        movieCatalogueDatabase.close()
    }

    @Test
    fun getMovieFavorite() = runBlocking {
        val movie = viewModel.getFavoriteMovies()
        Assert.assertNotNull(movie)
    }
}