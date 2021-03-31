package com.example.newsapi.di.modules

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.newsapi.data.ArticleRepo
import com.example.newsapi.ui.MainViewModel
import com.example.newsapi.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {
    companion object {
        @Provides
        fun provideMainViewModelFactory(articleRepo: ArticleRepo): ViewModelProvider.Factory =
            ViewModelProviderFactory(MainViewModel::class) {
                MainViewModel(articleRepo)
            }

        @Provides
        fun provideMainViewModel(
            factory: ViewModelProvider.Factory,
            storeOwner: ViewModelStoreOwner
        ): MainViewModel =
            ViewModelProvider(storeOwner, factory).get(MainViewModel::class.java)
    }
}
