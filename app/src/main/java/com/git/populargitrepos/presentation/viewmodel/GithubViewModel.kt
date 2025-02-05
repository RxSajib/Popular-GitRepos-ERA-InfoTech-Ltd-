package com.git.populargitrepos.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.populargitrepos.data.remote.dto.RepositoryListResponse
import com.git.populargitrepos.domain.usecase.GetRepositoryUseCase
import com.git.populargitrepos.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoryUseCase
) : ViewModel() {

    private val _repositoryState = MutableStateFlow<NetworkResult<RepositoryListResponse>>(NetworkResult.Loading())
    val repositoryState: StateFlow<NetworkResult<RepositoryListResponse>> = _repositoryState

    init {
        getRepositories()
    }

    fun getRepositories() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _repositoryState.value = NetworkResult.Loading()
                _repositoryState.value = getRepositoriesUseCase()
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }
}