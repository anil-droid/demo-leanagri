package com.demo.leanagri.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AppConverters {

    @TypeConverter
    fun listToString(source: ArrayList<LinkedTreeMap<Any, Any>>?): String? = Gson().toJson(source)

    @TypeConverter
    fun stringToList(source: String?): ArrayList<LinkedTreeMap<Any, Any>>? = Gson().fromJson(
        source,
        getListType<LinkedTreeMap<Any, Any>>()
    )

    private fun <T> getListType(): Type {

        return object : TypeToken<ArrayList<T>>(){}.type
    }
}