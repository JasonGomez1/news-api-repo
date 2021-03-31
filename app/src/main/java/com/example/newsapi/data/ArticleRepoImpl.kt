package com.example.newsapi.data

import com.example.newsapi.data.remote.ApiService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepoImpl @Inject constructor(
    private val service: ApiService
) : ArticleRepo {

    override fun getArticles() = flow {
        emit(
            service.getHeadlines(
                mapOf(
                    "country" to "us",
                    "category" to "entertainment",
                    "apiKey" to "5d6003661aa54da6acab03e5397edd00"
                )
            ).articles
        )
    }
}
