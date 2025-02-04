package com.git.populargitrepos.domain.repository.github_repository_list

import com.git.populargitrepos.data.remote.dto.RepositoryListResponse
import com.git.populargitrepos.utils.NetworkResult

interface GithubRepository {

    suspend fun getRepositoryList() : NetworkResult<RepositoryListResponse>
}