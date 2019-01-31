package com.skeleton.android.features.events

import com.skeleton.android.core.exception.Failure
import com.skeleton.android.core.functional.Either
import com.skeleton.android.core.functional.Either.Left
import com.skeleton.android.core.functional.Either.Right
import com.skeleton.android.core.platform.ServiceKOs
import java.lang.Exception
import javax.inject.Inject

interface EventsRepository {
    fun events(): Either<Failure, List<Event>>
    fun add(event: Event): Either<Failure, Any>

    class Network
    @Inject constructor(private val service: EventsService) : EventsRepository {

        override fun events(): Either<Failure, List<Event>> {
            return try {
                Right(service.events().map {
                    it.toEvent()
                })
            } catch (e: Exception) {
                Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }

        override fun add(event : Event): Either<Failure, Any> {
            return try {
                Right(service.add(event.toEventEntity()))
            } catch (e: Exception) {
                Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }
    }
}
