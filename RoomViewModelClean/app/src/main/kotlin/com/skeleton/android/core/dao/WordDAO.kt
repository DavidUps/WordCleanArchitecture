package com.skeleton.android.core.dao

import android.arch.persistence.room.*
import com.skeleton.android.features.word.WordEntity

@Dao
interface WordDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord (word: WordEntity)

    @Update
    fun updateWord(word: WordEntity)

    @Delete
    fun deleteWord(word: WordEntity)

    @Query("SELECT * FROM WordEntity")
    fun getWords(): List<WordEntity>

    @Query("SELECT * FROM EventEntity WHERE id == :wordId")
    fun getWordsById(wordId: Int): List<WordEntity>
}