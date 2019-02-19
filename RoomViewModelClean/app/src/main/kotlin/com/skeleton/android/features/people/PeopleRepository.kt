package com.skeleton.android.features.people

import com.skeleton.android.core.exception.Failure
import com.skeleton.android.core.functional.Either
import com.skeleton.android.core.platform.NetworkHandler
import retrofit2.Call
import javax.inject.Inject

interface PeopleRepository {
    fun getPeople(page: Int): Either<Failure, People>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val service: PeopleServices) : PeopleRepository {


        override fun getPeople(page: Int): Either<Failure, People> {

            /**
             * SI SE ENVIAN DATOS HABRIA QUE CREAR UNA CLASE REQUEST CON VARIABLES SERIALIZADAS
             * Y ENVIARLA EN EL SERVICE.
             */

            return when (networkHandler.isConnected) {
                true -> request(service.getPeople(page), {
                    it.toPeople()
                }, PeopleEntity.empty())
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> {
                        val errorMessage = response.errorBody()?.string()
                        Either.Left(Failure.CustomError(response.code(), errorMessage))
                    }
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError())
            }
        }
    }
}