package com.skeleton.android.features.events

import com.skeleton.android.features.consumable.Consumable
import com.skeleton.android.features.places.Place

data class Event(val id: Int,
                 val place: Place?,
                 val consumables: List<Consumable>,
                 val images: List<EventImage>) {

    fun toEventView(): EventView {
        return EventView(id,
                place?.toPlaceView(),
                consumables.map {
                    it.toConsumableView()
                },
                images.map {
                    it.toEventImageView()
                })
    }

    fun toEventEntity(): EventEntity {
        return EventEntity(id,
                place?.toPlaceEntity(),
                consumables.map {
                    it.toConsumableEntity()
                },
                images.map {
                    it.toEventImageEntity()
                })
    }

    companion object {
        fun empty() = Event(0, Place.empty(), emptyList(), emptyList())
    }
}