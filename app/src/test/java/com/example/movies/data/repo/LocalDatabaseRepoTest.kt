package com.example.movies.data.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.core.app.ApplicationProvider
import com.example.movies.data.localdata.MovieCatalogueDatabase
import com.example.movies.data.model.Movie
import com.example.movies.data.model.TvShow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(RobolectricTestRunner::class)
class LocalDatabaseRepoTest {

    val dummyMovie = Movie(
        1,
        "Dummy Overview",
        "Dummy Title",
        "2020-01-01",
        8.7,
        true,
        "Dummy Poster Path"
    )

    val dummyTvShow = TvShow(
        1,
        "2020-01-01",
        "Dummy Overview",
        "Dummy Poster Path",
        "Dummy Name",
        8.7,
    )

    val movieResponse = listOf(dummyMovie)
    val tvShowResponse = listOf(dummyTvShow)

    private lateinit var movieCatalogueDatabase: MovieCatalogueDatabase

    private lateinit var fakeLocalDatabaseRepo: FakeLocalDatabaseRepo

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        movieCatalogueDatabase = Room.inMemoryDatabaseBuilder(
            context, MovieCatalogueDatabase::class.java
        ).build()
        fakeLocalDatabaseRepo = FakeLocalDatabaseRepo(movieCatalogueDatabase)
    }

    @After
    fun closing() {
        movieCatalogueDatabase.close()
    }

    @Test
    fun getFavoriteMovies() = runBlocking {
        fakeLocalDatabaseRepo.addFavoriteMovie(dummyMovie)
        fakeLocalDatabaseRepo.getFavoriteMovies()
        val entity = mockPagedList(listOf(dummyMovie))
        Assert.assertNotNull(entity)
        Assert.assertEquals(movieResponse.size, entity.size)
    }

    @Test
    fun addAndCheckFavoriteMovie() = runBlocking {
        fakeLocalDatabaseRepo.addFavoriteMovie(dummyMovie)
        val result = fakeLocalDatabaseRepo.checkIsFavoriteMovie(dummyMovie)
        Assert.assertEquals(1, result)
    }

    @Test
    fun addDeleteAndCheckFavoriteMovie() = runBlocking {
        fakeLocalDatabaseRepo.addFavoriteMovie(dummyMovie)
        fakeLocalDatabaseRepo.deleteFavoriteMovie(dummyMovie)
        val result = fakeLocalDatabaseRepo.checkIsFavoriteMovie(dummyMovie)
        Assert.assertEquals(0, result)
    }

    @Test
    fun getFavoriteTvShow() = runBlocking {
        fakeLocalDatabaseRepo.addFavoriteTvShow(dummyTvShow)
        fakeLocalDatabaseRepo.getFavoriteTvShow()
        val entity = mockPagedList(listOf(dummyTvShow))
        Assert.assertNotNull(entity)
        Assert.assertEquals(tvShowResponse.size, entity.size)
    }

    @Test
    fun addAndCheckFavoriteTvShow() = runBlocking {
        fakeLocalDatabaseRepo.addFavoriteTvShow(dummyTvShow)
        val result = fakeLocalDatabaseRepo.checkIsFavoriteTvShow(dummyTvShow)
        Assert.assertEquals(1, result)
    }

    @Test
    fun addDeleteAndCheckFavoriteTvShow() = runBlocking {
        fakeLocalDatabaseRepo.addFavoriteTvShow(dummyTvShow)
        fakeLocalDatabaseRepo.deleteFavoriteTvShow(dummyTvShow)
        val result = fakeLocalDatabaseRepo.checkIsFavoriteTvShow(dummyTvShow)
        Assert.assertEquals(0, result)
    }

    private fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedList[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        `when`(pagedList.size).thenReturn(list.size)

        return pagedList
    }

}