package com.skeleton.android.features.word

import android.os.Parcel
import com.skeleton.android.core.platform.KParcelable
import com.skeleton.android.core.platform.parcelableCreator

data class WordView(var id: Int,
                    var word: String) : KParcelable {

    companion object {
        fun empty(): WordView = WordView(0, "")

        @JvmField
        val CREATOR = parcelableCreator(::WordView)
    }

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(word)
        }
    }
}