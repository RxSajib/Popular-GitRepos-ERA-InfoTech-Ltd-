package com.git.populargitrepos.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.git.populargitrepos.data.local.dao.Dao
import com.git.populargitrepos.data.local.database.GitHubDatabase
import com.git.populargitrepos.domain.model.Item
import com.git.populargitrepos.domain.model.Owner
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class DatabaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var gitHubDatabase: GitHubDatabase
    private lateinit var dao: Dao

    @Before
    fun setUp(){
        hiltAndroidRule.inject()
        dao = gitHubDatabase.githubDao()
    }

    @Test
    fun insert() = runTest {
        val githubRepository = Item(
            name = "Sajib Roy",
            id = 2,
            description = "Android Programmer Java/Kotlin",
            owner = Owner(
                avatar_url= "avator url",
                login = "Sajib Roy"
            ),
            topics = listOf("Java", "Kotlin", "C", "C++"),
            language = "Java/Kotlin",
            pushed_at = "12 JUN 2025",
            created_at = "10 AUG 2022",
            updated_at = "10 JUN 2025"
        )

        dao.insertRepositories(repositories = listOf(githubRepository))
        val result = dao.getAllRepositories()
        Assert.assertEquals(1, result.size)
    }

    @After
    fun close(){
        gitHubDatabase.close()
    }
}