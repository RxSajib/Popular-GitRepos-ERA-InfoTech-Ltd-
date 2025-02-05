package com.git.populargitrepos.utils

import androidx.room.TypeConverter
import com.git.populargitrepos.domain.model.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OwnerTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromOwner(owner: Owner?): String? {
        return gson.toJson(owner)
    }

    @TypeConverter
    fun toOwner(json: String?): Owner? {
        val type = object : TypeToken<Owner?>() {}.type
        return gson.fromJson(json, type)
    }
}