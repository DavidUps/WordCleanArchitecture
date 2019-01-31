package com.skeleton.android.features.word

import com.skeleton.android.core.dataBase.AppDatabase
import com.skeleton.android.core.platform.ContextHandler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordService
@Inject
constructor(contextHandler: ContextHandler): WordApi{
    private val wordApi by lazy {
        AppDatabase.getAppDataBase(contextHandler.appContext)?.wordEntityDao()
    }

    override fun words() = wordApi!!.getWords()
    override fun add(word: WordEntity) = wordApi!!.insertWord(word)
}