package com.skeleton.android.features.people

import com.skeleton.android.core.extension.empty

class People (val name: String){
    companion object {
        fun empty() = People(String.empty())
    }

    fun toPeopleView() = PeopleView(name)
}