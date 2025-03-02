package com.git.populargitrepos.presentation.di

import android.content.Context
import androidx.room.Room
import com.git.populargitrepos.common.DataManager
import com.git.populargitrepos.common.DataManager.GITHUB_DATABASE_ROOT
import com.git.populargitrepos.data.local.dao.Dao
import com.git.populargitrepos.data.local.database.GitHubDatabase
import com.git.populargitrepos.data.remote.api.GithubAPI
import com.git.populargitrepos.domain.repository.github_repository_list.GithubRepository
import com.git.populargitrepos.domain.repository.github_repository_list.GithubRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitHubClint {

    //todo add logger for network clint
    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    //todo add singleton instance for network clint
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(DataManager.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun provideAPI(retrofit: Retrofit): GithubAPI = retrofit.create(GithubAPI::class.java)


    //todo github api implement

    @Provides
    @Singleton
    fun provideDao(gitHubDatabase: GitHubDatabase) : Dao = gitHubDatabase.githubDao()




    @Singleton
    @Provides
    fun githubRepositoryImp(githubAPI: GithubAPI, dao: Dao, @ApplicationContext context: Context): GithubRepository =
        GithubRepositoryImp(api = githubAPI, repositoryDao = dao, context = context)

}