package com.example.newsapi.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.newsapi.data.ArticleRepo
import com.example.newsapi.data.remote.Article

class MainViewModel(private val repo: ArticleRepo) : ViewModel() {

    val articles: LiveData<List<Article>>
        get() = repo
            .getArticles()
            .asLiveData()
}
