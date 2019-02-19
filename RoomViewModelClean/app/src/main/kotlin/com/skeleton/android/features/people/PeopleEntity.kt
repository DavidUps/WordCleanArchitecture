package com.skeleton.android.features.people

import com.skeleton.android.core.extension.empty

class PeopleEntity (val name: String){
    companion object {
        fun empty() = PeopleEntity(String.empty())
    }
    fun toPeople() = People(name)
}