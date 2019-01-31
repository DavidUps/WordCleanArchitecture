package com.skeleton.android.core.dataBase

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.skeleton.android.core.extension.empty
import com.skeleton.android.features.consumable.ConsumableEntity

class ConsumableConverters {

    @TypeConverter
    fun toString(consumables: List<ConsumableEntity>?): String {
        if (consumables == null) {
            return String.empty()
        }
        return Gson().toJson(consumables)
    }

    @TypeConverter
    fun fromString(jsonList: String?): List<ConsumableEntity> {
        if (jsonList == null || jsonList.isEmpty()) {
            return emptyList()
        }
        val consumableEntityType = object : TypeToken<List<ConsumableEntity>>() {}.type
        return Gson().fromJson<List<ConsumableEntity>>(jsonList, consumableEntityType)
    }
}