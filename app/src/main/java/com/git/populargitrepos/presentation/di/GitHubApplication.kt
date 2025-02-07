package com.git.populargitrepos.presentation.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}