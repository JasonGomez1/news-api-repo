package com.example.newsapi.data

import com.example.newsapi.data.remote.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepo {
    fun getArticles(): Flow<List<Article>>
}