package com.skeleton.android.features.events

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class EventImageEntity(
        @PrimaryKey(autoGenerate = false)
        val id: Int,

        @ColumnInfo(name = "bucket_url")
        val bucket_url: String,

        @ColumnInfo(name = "extension")
        val extension: String,

        @ColumnInfo(name = "mime_type")
        val mime_type: String) {

    fun toEventImage(): EventImage = EventImage(id, bucket_url, extension, mime_type)
}