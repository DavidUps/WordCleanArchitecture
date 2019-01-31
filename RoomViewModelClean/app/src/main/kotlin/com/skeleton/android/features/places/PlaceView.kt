package com.skeleton.android.features.places

import android.os.Parcel
import com.skeleton.android.core.extension.empty
import com.skeleton.android.core.platform.KParcelable
import com.skeleton.android.core.platform.parcelableCreator

data class PlaceView(val id: Int,
                     val name: String) : KParcelable {

    companion object {
        fun empty(): PlaceView = PlaceView(0, String.empty())

        @JvmField
        val CREATOR = parcelableCreator(::PlaceView)
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
