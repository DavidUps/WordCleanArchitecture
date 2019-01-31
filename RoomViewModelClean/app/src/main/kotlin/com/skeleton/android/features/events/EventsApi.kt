package com.skeleton.android.features.events

internal interface EventsApi {

    fun events(): List<EventEntity>
    fun add(event : EventEntity): Any

}