package com.skeleton.android.features.word

data class Word(val id: Int,
                 val word: String) {

    fun toWordView(): WordView {
        return WordView(id, word)
    }

    fun toWordEntity(): WordEntity {
        return WordEntity(id, word)
    }

    companion object {
        fun empty() = Word(0, "")
    }
}