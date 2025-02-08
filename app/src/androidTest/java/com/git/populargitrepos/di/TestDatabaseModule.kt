package com.git.populargitrepos.di

import android.content.Context
import androidx.room.Room
import com.git.populargitrepos.data.local.database.GitHubDatabase
import com.git.populargitrepos.presentation.di.RoomModule
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@dagger.Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [RoomModule::class])
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideGithubTestDB(@ApplicationContext context : Context) : GitHubDatabase =
        Room.inMemoryDatabaseBuilder(
            context,
            GitHubDatabase::class.java
        ).allowMainThreadQueries().build()
}