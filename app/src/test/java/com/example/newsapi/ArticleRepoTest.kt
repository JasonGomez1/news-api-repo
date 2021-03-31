package com.example.newsapi

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsapi.data.ArticleRepoImpl
import com.example.newsapi.data.remote.ApiService
import com.example.newsapi.data.remote.Article
import com.example.newsapi.data.remote.TopHeadlinesResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleRepoTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var mockApiService: ApiService
    lateinit var articleRepo: ArticleRepoImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true, relaxed = true)
        Dispatchers.setMain(Dispatchers.Unconfined)
        articleRepo = ArticleRepoImpl(mockApiService)
    }

    @Test
    fun `emits list of articles when getHeadlines() is called`() = runBlocking<Unit> {
        val articles = listOf(
            Article(
                title = "One",
                author = "",
                source = null,
                description = "",
                url = "",
                urlToImage = "",
                publishedAt = "",
                content = ""
            ),
            Article(
                title = "Two",
                author = "",
                source = null,
                description = "",
                url = "",
                urlToImage = "",
                publishedAt = "",
                content = ""
            )
        )
        coEvery { mockApiService.getHeadlines(ofType()) } returns TopHeadlinesResponse(
            status = "",
            totalResults = 10,
            articles = articles
        )
        assertThat(articleRepo.getArticles().single()).containsExactlyElementsIn(articles)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
