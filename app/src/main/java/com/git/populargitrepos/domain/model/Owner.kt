package com.git.populargitrepos.domain.model


import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class Owner(
    val avatar_url: String?,
    val login: String?,
) : Parcelable