package com.example.newsapi

import android.app.Application
import com.example.newsapi.di.components.DaggerAppComponent

class App: Application() {

    val appComponent by lazy {
        DaggerAppComponent
            .factory()
            .create(this)
    }
}
