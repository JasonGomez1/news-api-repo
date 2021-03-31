package com.example.newsapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi.App
import com.example.newsapi.R
import com.example.newsapi.databinding.ActivityMainBinding
import com.example.newsapi.utils.ARTICLE_TAG
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    private val articleAdapter: ArticleAdapter = ArticleAdapter {
        supportFragmentManager.commit(allowStateLoss = true) {
            setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.slide_out)
            binding.apply {
                addToBackStack(ARTICLE_TAG)
                replace(fragmentContainer.id, ArticleFragment.newInstance(it.url), ARTICLE_TAG)
                fragmentContainer.visibility = View.VISIBLE
            }
        }
    }

    private val activityComponent by lazy {
        (application as App)
            .appComponent
            .activityComponent
            .create(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activityComponent.inject(this)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity).also {
                addItemDecoration(DividerItemDecoration(context, it.orientation))
            }
            adapter = articleAdapter

        }

        viewModel.articles.observe(this) {
            articleAdapter.submitList(it)
        }
    }
}
