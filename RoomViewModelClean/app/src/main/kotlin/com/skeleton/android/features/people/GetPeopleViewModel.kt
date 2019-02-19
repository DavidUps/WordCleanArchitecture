package com.skeleton.android.features.people

import android.arch.lifecycle.MutableLiveData
import com.skeleton.android.core.platform.BaseViewModel
import javax.inject.Inject

class GetPeopleViewModel
@Inject constructor(private val getPeople: GetPeople): BaseViewModel(){

    var people: MutableLiveData<PeopleView> = MutableLiveData()

    fun getPeople(page: Int){
        getPeople.invoke(GetPeople.Params(page)){
            it.either(::handleFailure, ::handleGetPeople)
        }
    }
    private fun handleGetPeople(people: People){
        this.people.value = people.toPeopleView()
    }
}