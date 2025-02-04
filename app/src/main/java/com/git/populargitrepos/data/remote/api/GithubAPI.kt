package com.git.populargitrepos.data.remote.api


import com.git.populargitrepos.common.DataManager.ORDER
import com.git.populargitrepos.common.DataManager.SEARCH_REPOSITORY
import com.git.populargitrepos.common.DataManager.SHORT
import com.git.populargitrepos.data.remote.dto.RepositoryListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubAPI {
    @GET("repositories")
    suspend fun repositoryList(
        @Query("q") search : String = SEARCH_REPOSITORY,
        @Query("sort") short : String = SHORT,
        @Query("order") order : String = ORDER
    ) : Response<RepositoryListResponse>


}