package com.skeleton.android.features.events

import com.skeleton.android.core.extension.empty

data class EventImage(val id: Int,
                      var bucket_url: String,
                      val extension: String,
                      val mime_type: String) {

    fun toEventImageView(): EventImageView = EventImageView(id, bucket_url, extension, mime_type)

    fun toEventImageEntity(): EventImageEntity = EventImageEntity(id, bucket_url, extension, mime_type)


    companion object {
        fun empty() = EventImage(0, String.empty(), String.empty(), String.empty())
    }
}