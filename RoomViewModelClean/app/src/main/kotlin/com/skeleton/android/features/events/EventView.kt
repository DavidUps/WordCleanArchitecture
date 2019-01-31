package com.skeleton.android.features.events

import android.os.Parcel
import com.skeleton.android.core.platform.KParcelable
import com.skeleton.android.core.platform.parcelableCreator
import com.skeleton.android.core.platform.readTypedObjectCompat
import com.skeleton.android.core.platform.writeTypedObjectCompat
import com.skeleton.android.features.consumable.ConsumableView
import com.skeleton.android.features.places.PlaceView

data class EventView(var id: Int,
                     var place: PlaceView?,
                     var consumables: List<ConsumableView>,
                     var images: List<EventImageView>) : KParcelable {

    companion object {
        fun empty(): EventView = EventView(0, PlaceView.empty(), emptyList(), emptyList())

        @JvmField
        val CREATOR = parcelableCreator(::EventView)
    }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readTypedObjectCompat<PlaceView>(PlaceView.CREATOR),
            listOf<ConsumableView>().apply {
                parcel.readList(this, ConsumableView::class.java.classLoader)
            },
            listOf<EventImageView>().apply {
                parcel.readList(this, EventImageView::class.java.classLoader)
            })

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeTypedObjectCompat(place, flags)
            writeList(consumables)
            writeList(images)
        }
    }
}