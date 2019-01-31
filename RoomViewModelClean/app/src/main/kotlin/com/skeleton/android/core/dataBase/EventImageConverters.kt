package com.skeleton.android.core.dataBase

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.skeleton.android.core.extension.empty
import com.skeleton.android.features.events.EventImageEntity

class EventImageConverters {

    @TypeConverter
    fun toString(consumables: List<EventImageEntity>?): String {
        if (consumables == null) {
            return String.empty()
        }
        return Gson().toJson(consumables)
    }

    @TypeConverter
    fun fromString(jsonList: String?): List<EventImageEntity> {
        if (jsonList == null || jsonList.isEmpty()) {
            return emptyList()
        }
        val eventImageEntityType = object : TypeToken<List<EventImageEntity>>() {}.type
        return Gson().fromJson<List<EventImageEntity>>(jsonList, eventImageEntityType)
    }
}