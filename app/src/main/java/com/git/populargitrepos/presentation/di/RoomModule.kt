package com.git.populargitrepos.presentation.di

import android.content.Context
import androidx.room.Room
import com.git.populargitrepos.common.DataManager.GITHUB_DATABASE_ROOT
import com.git.populargitrepos.data.local.database.GitHubDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    //todo room database implementation
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        GitHubDatabase::class.java,
        GITHUB_DATABASE_ROOT
    ).fallbackToDestructiveMigration()
        .build()
}