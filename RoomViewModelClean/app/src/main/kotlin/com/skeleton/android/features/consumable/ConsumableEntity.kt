package com.skeleton.android.features.consumable

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.skeleton.android.core.extension.empty

@Entity
data class ConsumableEntity(

        @PrimaryKey(autoGenerate = false)
        val id: Int,

        @ColumnInfo(name = "Name")
        val name: String) {

    companion object {
        fun empty() = ConsumableEntity(0, String.empty())
    }

    fun toConsumable(): Consumable = Consumable(id, name)
}
