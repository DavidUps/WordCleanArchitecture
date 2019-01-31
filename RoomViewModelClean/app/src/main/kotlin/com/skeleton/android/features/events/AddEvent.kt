package com.skeleton.android.features.events

import com.skeleton.android.core.interactor.UseCase
import javax.inject.Inject

class AddEvent
@Inject constructor(private val eventsRepository: EventsRepository) : UseCase<Any, AddEvent.Params>() {

    override suspend fun run(params: Params) = eventsRepository.add(params.event)

    class Params(val event: Event)

}