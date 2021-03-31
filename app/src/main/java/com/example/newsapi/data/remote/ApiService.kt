package com.example.newsapi.data.remote

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("top-headlines")
    suspend fun getHeadlines(@QueryMap queryParams: Map<String, String>): TopHeadlinesResponse

}
