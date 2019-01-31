package com.skeleton.android.features.places

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.skeleton.android.core.extension.empty

@Entity
data class PlaceEntity(
        @PrimaryKey(autoGenerate = false)
        val id: Int,

        @ColumnInfo(name = "Name")
        val name: String) {

    companion object {
        fun empty() = PlaceEntity(0, String.empty())
    }

    fun toPlace() = Place(id, name)
}
