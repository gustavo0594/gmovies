package com.gquesada.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}