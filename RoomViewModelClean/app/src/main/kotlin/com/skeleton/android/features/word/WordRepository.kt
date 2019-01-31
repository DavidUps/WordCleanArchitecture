package com.skeleton.android.features.word

import com.skeleton.android.core.exception.Failure
import com.skeleton.android.core.functional.Either
import com.skeleton.android.core.platform.ServiceKOs
import java.lang.Exception
import javax.inject.Inject

interface WordRepository {

    fun words(): Either<Failure, List<Word>>
    fun add(word: Word): Either<Failure, Any>

    class Network
    @Inject constructor(private val service: WordService): WordRepository{
        override fun words(): Either<Failure, List<Word>> {
            return try {
                Either.Right(service.words().map {
                    it.toWord()
                })
            } catch (e: Exception) {
                Either.Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }

        override fun add(word: Word): Either<Failure, Any> {
            return try {
                Either.Right(service.add(word.toWordEntity()))
            } catch (e: Exception){
                Either.Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }
    }
}