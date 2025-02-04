package com.git.populargitrepos.domain.model


import androidx.annotation.Keep

@Keep
data class License(
    val key: String?,
    val name: String?,
    val node_id: String?,
    val spdx_id: String?,
    val url: String?
)