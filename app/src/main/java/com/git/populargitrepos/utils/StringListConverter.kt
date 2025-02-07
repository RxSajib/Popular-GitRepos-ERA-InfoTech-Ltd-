package com.git.populargitrepos.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(list: List<String?>?): String? {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String?): List<String?>? {
        return json?.let {
            val type = object : TypeToken<List<String?>>() {}.type
            gson.fromJson(it, type)
        }
    }
}