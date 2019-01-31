package com.skeleton.android.features.word

import android.arch.lifecycle.MutableLiveData
import com.skeleton.android.core.platform.BaseViewModel
import javax.inject.Inject

class GetWordViewModel
@Inject constructor(private val getWord: GetWord): BaseViewModel(){

    var words: MutableLiveData<List<WordView>> = MutableLiveData()

    fun words() = getWord.invoke(
            GetWord.Params()){
        it.either(::handleFailure, ::handleWordList)
    }

    private fun handleWordList(words: List<Word>){
        this.words.value = words.map {
            it.toWordView()
        }
    }
}