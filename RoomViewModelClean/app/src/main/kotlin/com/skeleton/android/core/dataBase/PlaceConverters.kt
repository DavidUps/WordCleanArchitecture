package com.skeleton.android.core.dataBase

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.skeleton.android.core.extension.empty
import com.skeleton.android.features.places.PlaceEntity


class PlaceConverters {

    @TypeConverter
    fun toString(place: PlaceEntity?): String {
        if (place == null) {
            return String.empty()
        }
        return Gson().toJson(place)
    }

    @TypeConverter
    fun fromString(json: String?): PlaceEntity {
        if (json == null || json.isEmpty()) {
            return PlaceEntity.empty()
        }
        return Gson().fromJson<PlaceEntity>(json, PlaceEntity::class.java)
    }
}