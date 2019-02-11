package com.skeleton.android.features.word

import com.skeleton.android.core.interactor.UseCase
import javax.inject.Inject

class AddWord
@Inject constructor(private val wordRepository: WordRepository): UseCase<Any, AddWord.Params>(){

    override suspend fun run(params: Params) = wordRepository.add(params.word)

    class Params(val word: Word)


}