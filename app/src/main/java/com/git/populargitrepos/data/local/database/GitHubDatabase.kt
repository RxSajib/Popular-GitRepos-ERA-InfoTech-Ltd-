package com.git.populargitrepos.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.git.populargitrepos.data.local.dao.Dao
import com.git.populargitrepos.domain.model.Item
import com.git.populargitrepos.utils.LicenseTypeConverter
import com.git.populargitrepos.utils.OwnerTypeConverter
import com.git.populargitrepos.utils.StringListConverter

@Database(entities = [Item::class], version = 972  , exportSchema = false)
@TypeConverters( OwnerTypeConverter::class, StringListConverter::class)  //todo Add typeconverter
//LicenseTypeConverter::class,
abstract class GitHubDatabase : RoomDatabase() {

    abstract fun githubDao(): Dao
}