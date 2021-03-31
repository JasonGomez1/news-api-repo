package com.example.newsapi.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapi.data.remote.Article

class ArticleDiffUtil : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Article, newItem: Article) =
        oldItem == newItem
}
