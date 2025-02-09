package com.git.populargitrepos.api_test

import com.git.populargitrepos.data.remote.api.GithubAPI
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GithubApiTest {

    private lateinit var mockWebServer : MockWebServer
    private lateinit var githubAPI: GithubAPI


    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        githubAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Use mock server URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubAPI::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }


    @Test
    fun `test repository list API success response`() = runBlocking {
        // Mock API Response
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""{
                "total_count": 1,
                "incomplete_results": false,
                "items": [{
                    "id": 12345,
                    "name": "TestRepo",
                    "description": "This is a test repository",
                    "owner": {
                        "login": "testUser",
                        "avatar_url": "https://avatar.url"
                    },
                    "topics": ["kotlin", "android"],
                    "pushed_at": "2024-02-01T12:00:00Z",
                    "created_at": "2023-01-01T12:00:00Z",
                    "updated_at": "2024-01-15T12:00:00Z",
                    "language": "Kotlin"
                }]
            }""")

        mockWebServer.enqueue(mockResponse)

        // Make API call
        val response = githubAPI.repositoryList()
        Assert.assertEquals(200, response.code())
        // Assertions
     //   assertThat(response.isSuccessful).isTrue()
     //   assertThat(response.body()).isNotNull()
     //   assertThat(response.body()?.items?.size).isEqualTo(1)
     //   assertThat(response.body()?.items?.get(0)?.name).isEqualTo("TestRepo")
    }


    @Test
    fun `test repository list API error response`() = runBlocking {
        // Mock 404 Response
        val mockResponse = MockResponse().setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        // Make API call
        val response = githubAPI.repositoryList()
        Assert.assertEquals(404, response.code())
        // Assertions
    //    assertThat(response.isSuccessful).isFalse()
     //   assertThat(response.code()).isEqualTo(404)
    }
}