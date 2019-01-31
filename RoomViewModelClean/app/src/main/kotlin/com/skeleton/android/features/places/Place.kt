package com.skeleton.android.features.places

import com.skeleton.android.core.extension.empty


data class Place(val id: Int,
                 val name: String) {

    companion object {
        fun empty() = Place(0, String.empty())
    }

    fun toPlaceView() = PlaceView(id, name)

    fun toPlaceEntity() = PlaceEntity(id, name)

}
