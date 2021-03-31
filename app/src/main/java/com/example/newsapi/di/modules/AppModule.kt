package com.example.newsapi.di.modules

import com.example.newsapi.data.ArticleRepo
import com.example.newsapi.data.ArticleRepoImpl
import com.example.newsapi.data.remote.ApiService
import com.example.newsapi.utils.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds abstract fun provideArticleRepo(articleRepoImpl: ArticleRepoImpl): ArticleRepo

    companion object {
        @Provides
        @Singleton
        fun provideApiService(): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build().create(ApiService::class.java)
        }
    }
}
