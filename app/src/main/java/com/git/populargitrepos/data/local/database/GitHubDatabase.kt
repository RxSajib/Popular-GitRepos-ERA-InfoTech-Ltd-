package com.git.populargitrepos.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.git.populargitrepos.data.local.dao.Dao
import com.git.populargitrepos.domain.model.Item
import com.git.populargitrepos.utils.LicenseTypeConverter
import com.git.populargitrepos.utils.OwnerTypeConverter

@Database(entities = [Item::class], version = 1, exportSchema = false)
@TypeConverters(LicenseTypeConverter::class, OwnerTypeConverter::class)  //todo Add typeconverter

abstract class GitHubDatabase : RoomDatabase() {

    abstract fun githubDao(): Dao
}