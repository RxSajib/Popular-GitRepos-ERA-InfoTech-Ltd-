package com.git.populargitrepos.domain.repository.github_repository_list

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.git.populargitrepos.data.local.dao.Dao
import com.git.populargitrepos.data.remote.api.GithubAPI
import com.git.populargitrepos.data.remote.dto.RepositoryListResponse
import com.git.populargitrepos.domain.model.Item
import com.git.populargitrepos.domain.model.Owner
import com.git.populargitrepos.utils.NetworkResult
import java.net.UnknownHostException

private const val TAG = "GithubRepositoryImp"

class GithubRepositoryImp(
    private val api: GithubAPI,
    private val repositoryDao: Dao,
    private val context: Context
) : GithubRepository {

    override suspend fun getRepositoryList(): NetworkResult<RepositoryListResponse> {
        return try {
            if (isNetworkAvailable(context)) { // Check network connectivity
                val response = api.repositoryList()
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let { apiResponse ->
                        val repositoryEntities = apiResponse.items?.mapNotNull { item ->
                            item?.let {
                                Item(
                                    id = it.id ?: 0,  //todo Provide default value if null
                                    name = it.name ?: "Unknown",
                                    description = it.description ?: "No description",
                                    owner = it.owner?.let { owner ->
                                        Owner(avatar_url = owner.avatar_url ?: "", login = owner.login)
                                    },
                                    topics = it.topics,
                                    pushed_at = it.pushed_at,
                                    created_at = it.created_at,
                                    updated_at = it.updated_at,
                                    language = it.language
                                )
                            }
                        } ?: emptyList()

                        //todo Cache in Room Database
                        repositoryDao.clearRepositories()
                        repositoryDao.insertRepositories(repositoryEntities)

                        NetworkResult.Success(
                            data = RepositoryListResponse(
                                items = repositoryEntities,
                                incomplete_results = true,
                                total_count = 2
                            )
                        )
                    } ?: NetworkResult.Empty()
                } else {
                    NetworkResult.Error("Error: ${response.code()} ${response.message()}")
                }
            } else {
                //todo Fetch data from Room if offline
                val cachedRepositories = repositoryDao.getAllRepositories()
                if (cachedRepositories.isNotEmpty()) {
                    NetworkResult.Success(
                        RepositoryListResponse(
                            items = cachedRepositories,
                            total_count = 56,
                            incomplete_results = true
                        )
                    )
                } else {
                    NetworkResult.Error("No Internet & No Cached Data Found")
                }
            }
        } catch (e : UnknownHostException){
            NetworkResult.Error(message = "No internet connection "+e.message)
        }
        catch (e: Exception) {
            NetworkResult.Error(message = e.message ?: "Unknown error")
        }
    }


    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}