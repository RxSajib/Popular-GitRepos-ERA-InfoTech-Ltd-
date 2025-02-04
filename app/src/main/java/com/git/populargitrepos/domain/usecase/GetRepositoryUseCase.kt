package com.git.populargitrepos.domain.usecase

import com.git.populargitrepos.data.remote.dto.RepositoryListResponse
import com.git.populargitrepos.domain.repository.github_repository_list.GithubRepository
import com.git.populargitrepos.utils.NetworkResult
import javax.inject.Inject

class GetRepositoryUseCase @Inject constructor(private val repository: GithubRepository) {
    suspend operator fun invoke() : NetworkResult<RepositoryListResponse> = repository.getRepositoryList()
}