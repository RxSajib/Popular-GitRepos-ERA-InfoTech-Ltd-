package com.git.populargitrepos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.git.populargitrepos.domain.model.Item

@Dao
interface Dao {

    @Query("SELECT * FROM RepositoryDB")
    suspend fun getAllRepositories(): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repositories: List<Item>)

    @Query("DELETE FROM RepositoryDB")
    suspend fun clearRepositories()
}