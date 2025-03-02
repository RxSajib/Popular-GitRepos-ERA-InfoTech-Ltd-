package com.git.populargitrepos.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.git.populargitrepos.data.local.dao.Dao
import com.git.populargitrepos.domain.model.Item
import com.git.populargitrepos.utils.OwnerTypeConverter
import com.git.populargitrepos.utils.StringListConverter

@Database(entities = [Item::class], version = 951  , exportSchema = false)
@TypeConverters( OwnerTypeConverter::class, StringListConverter::class)  //todo Add typeconverter

abstract class GitHubDatabase : RoomDatabase() {

    abstract fun githubDao(): Dao
}