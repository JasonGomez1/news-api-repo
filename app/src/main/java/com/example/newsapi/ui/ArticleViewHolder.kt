package com.example.newsapi.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapi.data.remote.Article
import com.example.newsapi.databinding.ArticleBinding

class ArticleViewHolder(
    private val binding: ArticleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: Article, onClick: (Article) -> Unit) {
        binding.apply {
            tvTitle.text = article.title
            tvAuthor.text = article.author
            tvDescription.text = article.description
            root.setOnClickListener {
                onClick(article)
            }

            val progressDrawable = CircularProgressDrawable(binding.root.context)
            progressDrawable.strokeWidth = 5f
            progressDrawable.centerRadius = 30f
            progressDrawable.start()

            val options = RequestOptions()
            options.centerCrop()
            options.placeholder(progressDrawable)

            Glide.with(binding.root.context)
                .load(article.urlToImage)
                .apply(options)
                .into(ivImage)
        }
    }

}
