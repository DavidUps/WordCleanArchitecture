package com.skeleton.android.features.events

import com.skeleton.android.core.dataBase.AppDatabase
import com.skeleton.android.core.platform.ContextHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventsService
@Inject
constructor(contextHandler: ContextHandler) : EventsApi {

    /*
    private val eventAPI by lazy {
        AppDatabase.getAppDataBase(contextHandler.appContext)?.eventEntityDao()!!
    }

    override fun events() = eventAPI.getEvents()
    override fun add(event: EventEntity) = eventAPI.insertEvent(event)
    */

}