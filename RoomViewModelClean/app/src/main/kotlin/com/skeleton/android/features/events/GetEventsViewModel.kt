package com.skeleton.android.features.events

import android.arch.lifecycle.MutableLiveData
import com.skeleton.android.core.platform.BaseViewModel
import javax.inject.Inject

class GetEventsViewModel
@Inject constructor(private val getEvents: GetEvents) : BaseViewModel() {

    var events: MutableLiveData<List<EventView>> = MutableLiveData()

    fun events() = getEvents.invoke(
            GetEvents.Params()) {
        it.either(::handleFailure, ::handleEventList)
    }

    private fun handleEventList(events: List<Event>) {
        this.events.value = events.map { it ->
            it.toEventView()
        }
    }
}