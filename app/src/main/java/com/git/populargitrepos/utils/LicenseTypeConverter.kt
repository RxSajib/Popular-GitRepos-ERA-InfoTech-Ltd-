package com.git.populargitrepos.utils

import androidx.room.TypeConverter
import com.git.populargitrepos.domain.model.License
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LicenseTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromLicense(license: License?): String? {
        return gson.toJson(license)
    }

    @TypeConverter
    fun toLicense(json: String?): License? {
        val type = object : TypeToken<License?>() {}.type
        return gson.fromJson(json, type)
    }
}