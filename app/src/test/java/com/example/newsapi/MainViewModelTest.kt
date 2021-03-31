package com.example.newsapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.newsapi.data.ArticleRepo
import com.example.newsapi.data.remote.Article
import com.example.newsapi.ui.MainViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockArticleRepo: ArticleRepo

    @MockK
    lateinit var mockObserver: Observer<List<Article>>
    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)
        Dispatchers.setMain(Dispatchers.Unconfined)
        mainViewModel = MainViewModel(mockArticleRepo)
    }

    @Test
    fun `emits articles when livedata is observed`() {
        every { mockArticleRepo.getArticles() } returns flowOf(listOf(mockk()))
        mainViewModel.articles.observeForever(mockObserver)
        verify { mockArticleRepo.getArticles() }
        verify { mockObserver.onChanged(ofType()) }
        mainViewModel.articles.removeObserver(mockObserver)
    }

    @Test
    fun `emits every article from getArticles()`() {
        val slot = slot<List<Article>>()
        val articleOne = Article(
            title = "One",
            author = "",
            source = null,
            description = "",
            url = "",
            urlToImage = "",
            publishedAt = "",
            content = ""
        )
        val articleTwo = Article(
            title = "Two",
            author = "",
            source = null,
            description = "",
            url = "",
            urlToImage = "",
            publishedAt = "",
            content = ""
        )
        every { mockArticleRepo.getArticles() } returns flowOf(listOf(articleOne, articleTwo))
        mainViewModel.articles.observeForever(mockObserver)
        verifyAll {
            mockObserver.onChanged(capture(slot))
            mockArticleRepo.getArticles()
        }
        assertThat(slot.captured).containsExactly(articleOne, articleTwo)
        mainViewModel.articles.removeObserver(mockObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
