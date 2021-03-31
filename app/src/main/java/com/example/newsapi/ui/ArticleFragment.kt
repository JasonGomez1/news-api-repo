package com.example.newsapi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.newsapi.databinding.FragmentArticleBinding

private const val ARTICLE_URL = "articleUrl"

class ArticleFragment : Fragment() {
    private lateinit var articleUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            articleUrl = it.getString(ARTICLE_URL) ?: "https://newsapi.org/"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentArticleBinding.inflate(inflater, container, false).run {
        webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(articleUrl)
        }
        root
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String?) =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARTICLE_URL, url)
                }
            }
    }
}