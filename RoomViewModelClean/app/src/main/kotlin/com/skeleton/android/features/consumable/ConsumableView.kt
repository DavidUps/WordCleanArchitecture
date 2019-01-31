package com.skeleton.android.features.consumable

import android.os.Parcel
import com.skeleton.android.core.platform.KParcelable
import com.skeleton.android.core.platform.parcelableCreator

data class ConsumableView(val id: Int,
                          val name: String) : KParcelable {

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::ConsumableView)
    }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(name)
        }
    }
}
