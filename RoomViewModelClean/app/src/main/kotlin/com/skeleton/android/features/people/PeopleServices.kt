package com.skeleton.android.features.people

 import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleServices
@Inject constructor(retrofit: Retrofit) : PeopleApi {

    private val peopleApi by lazy {
        retrofit.create(PeopleApi::class.java)
    }

    override fun getPeople(page: Int) = peopleApi.getPeople(page)

}