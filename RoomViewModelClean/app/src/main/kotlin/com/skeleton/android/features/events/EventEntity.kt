package com.skeleton.android.features.events
import android.arch.persistence.room.*
import com.skeleton.android.features.consumable.ConsumableEntity
import com.skeleton.android.features.places.PlaceEntity

@Entity
data class EventEntity(
        @PrimaryKey(autoGenerate = false)
        val id: Int,

        @ColumnInfo(name = "Place")
        val place: PlaceEntity?,

        @ColumnInfo(name = "Consumables")
        val consumables: List<ConsumableEntity>,

        @ColumnInfo(name = "Images")
        val images: List<EventImageEntity>) {

    companion object {
        fun empty() = EventEntity(0, PlaceEntity.empty(), emptyList(), emptyList())
    }

    fun toEvent(): Event {
        return Event(id,
                place?.toPlace(),
                consumables.map {
                    it.toConsumable()
                },
                images.map {
                    it.toEventImage()
                }
        )
    }
}