package com.skeleton.android.features.word

internal interface WordApi {
    fun words(): List<WordEntity>
    fun add(word: WordEntity): Any
}