package com.git.populargitrepos.domain.model


import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "RepositoryDB")
@Parcelize
@Keep
data class Item(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val created_at: String?,
    val description: String?,
    val language: String?,
    val name: String?,
    val owner: Owner?,
    val pushed_at: String?,
    val topics: List<String?>?,
    val updated_at: String?,

) : Parcelable