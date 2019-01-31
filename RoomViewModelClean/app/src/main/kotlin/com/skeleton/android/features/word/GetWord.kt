package com.skeleton.android.features.word

import com.skeleton.android.core.interactor.UseCase
import javax.inject.Inject

class GetWord
@Inject constructor(private val wordRepository: WordRepository): UseCase<List<Word>, GetWord.Params>(){
    override suspend fun run(params: GetWord.Params) = wordRepository.words()

    class Params
}