package com.example.newsapi.di.components

import androidx.lifecycle.ViewModelStoreOwner
import com.example.newsapi.di.ActivityScope
import com.example.newsapi.di.modules.ActivityModule
import com.example.newsapi.ui.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance storeOwner: ViewModelStoreOwner): ActivityComponent
    }
}