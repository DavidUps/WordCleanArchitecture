package com.skeleton.android.features.word

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class WordEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "Word")
    val word: String){

    fun toWord(): Word{
        return Word(id,word)
    }
}