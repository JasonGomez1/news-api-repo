package com.example.newsapi.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)