package com.skeleton.android.features.people

import retrofit2.Call
import retrofit2.http.*

interface PeopleApi {

    companion object {
        private const val PEOPLE = "people/{page}"
    }

    @POST(PEOPLE)
    fun getPeople(@Path("page") page: Int): Call<PeopleEntity>
}