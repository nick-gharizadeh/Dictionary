package com.example.hw14.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WordDao {

    @Insert
    fun insert(word: Word?)

    @Update
    fun updateWords(vararg words: Word?)

    @Query("DELETE FROM word")
    fun deleteAll()


    @Query("SELECT * from word ORDER BY wordTitle ASC")
    fun getAllWords(): LiveData<List<Word?>?>?

    @Query("SELECT * FROM word WHERE wordTitle LIKE :word ")
    fun findWord(word: String?): List<Word?>?



}