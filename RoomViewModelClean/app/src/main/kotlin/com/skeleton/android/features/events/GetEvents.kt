package com.skeleton.android.features.events

import com.skeleton.android.core.interactor.UseCase
import javax.inject.Inject

class GetEvents
@Inject constructor(private val eventsRepository: EventsRepository) : UseCase<List<Event>, GetEvents.Params>() {

    override suspend fun run(params: Params) = eventsRepository.events()

    class Params

}