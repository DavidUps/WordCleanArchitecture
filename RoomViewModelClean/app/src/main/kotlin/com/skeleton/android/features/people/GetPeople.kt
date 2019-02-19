package com.skeleton.android.features.people

import com.skeleton.android.core.interactor.UseCase
import javax.inject.Inject

class GetPeople
@Inject constructor(private val peopleRepository: PeopleRepository): UseCase<People, GetPeople.Params>(){

    override suspend fun run(params: Params) = peopleRepository.getPeople(params.page)

    data class Params(val page: Int)

}
