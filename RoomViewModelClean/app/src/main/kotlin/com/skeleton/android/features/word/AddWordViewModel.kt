package com.skeleton.android.features.word

import android.arch.lifecycle.MutableLiveData
import com.skeleton.android.core.platform.BaseViewModel
import javax.inject.Inject

class AddWordViewModel
@Inject constructor(private val addWord: AddWord): BaseViewModel(){

    var trigger: MutableLiveData<Any> = MutableLiveData()

    fun add(word: Word) = addWord.invoke(
            AddWord.Params(word)){
        it.either(::handleFailure, ::notify)
    }

    private fun notify(any: Any){
        this.trigger.value= any
    }
}