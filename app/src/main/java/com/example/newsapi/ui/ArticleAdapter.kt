package com.example.newsapi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.newsapi.data.remote.Article
import com.example.newsapi.databinding.ArticleBinding

class ArticleAdapter(
    private val onClick: (Article) -> Unit
) : ListAdapter<Article, ArticleViewHolder>(ArticleDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleViewHolder(
            ArticleBinding
                .inflate(
                    LayoutInflater
                        .from(parent.context), parent, false
                )
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        getItem(position).let {
            holder.bind(it, onClick)
        }
}
