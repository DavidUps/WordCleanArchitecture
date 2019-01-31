package com.skeleton.android.features.events

import android.os.Parcel
import com.skeleton.android.core.platform.KParcelable
import com.skeleton.android.core.platform.parcelableCreator

data class EventImageView(val id: Int,
                          val bucket_url: String,
                          val extension: String,
                          val mime_type: String) : KParcelable {

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::EventImageView)
    }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(bucket_url)
            writeString(extension)
            writeString(mime_type)
        }
    }
}
