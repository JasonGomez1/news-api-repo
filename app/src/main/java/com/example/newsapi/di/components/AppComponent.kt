package com.example.newsapi.di.components

import android.content.Context
import com.example.newsapi.App
import com.example.newsapi.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    val activityComponent: ActivityComponent.Factory
    fun inject(app: App)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}