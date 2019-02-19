package com.skeleton.android.features.people

import android.os.Parcel
import com.skeleton.android.core.platform.KParcelable
import com.skeleton.android.core.platform.parcelableCreator

class PeopleView(val name: String): KParcelable{

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::PeopleView)
    }

    constructor(parcel: Parcel): this (
            parcel.toString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest){
            writeString(name)
        }
    }
}