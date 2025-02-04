package com.git.populargitrepos.data.remote.dto


import androidx.annotation.Keep
import com.git.populargitrepos.domain.model.Item

@Keep
data class RepositoryListResponse(
    val incomplete_results: Boolean?,
    val items: List<Item?>?,
    val total_count: Int?
)