package com.git.populargitrepos.domain.repository.github_repository_list

import com.git.populargitrepos.data.remote.api.GithubAPI
import com.git.populargitrepos.data.remote.dto.RepositoryListResponse
import com.git.populargitrepos.utils.NetworkResult

class GithubRepositoryImp(
    private val api: GithubAPI
) : GithubRepository {
    override suspend fun getRepositoryList(): NetworkResult<RepositoryListResponse> {
        return try {
            val response = api.repositoryList()
            if(response.isSuccessful && response.body() != null){
                response.body()?.let {
                    NetworkResult.Success(data = it)
                } ?: NetworkResult.Empty()
            }else {
                NetworkResult.Error("Error: ${response.code()} ${response.message()}")
            }

        }catch (e : Exception){
            NetworkResult.Error(message = e.message?: "")
        }
    }
}