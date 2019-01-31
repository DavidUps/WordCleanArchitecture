package com.skeleton.android.features.events

import android.arch.lifecycle.MutableLiveData
import com.skeleton.android.core.platform.BaseViewModel
import javax.inject.Inject

class AddEventViewModel
@Inject constructor(private val addEvent: AddEvent) : BaseViewModel() {

    var trigger: MutableLiveData<Any> = MutableLiveData()

    fun add(event: Event) = addEvent.invoke(
            AddEvent.Params(event)) {
        it.either(::handleFailure, ::notify)
    }

    private fun notify(any: Any) {
        this.trigger.value = any
    }
}