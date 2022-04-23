package com.example.hw14.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WordDao {

    @Insert
    fun insert(word: Word?)

    @Update
    fun updateWord(word: Word?)

    @Query("DELETE FROM word")
    fun deleteAll()

    @Query("DELETE FROM word WHERE wordTitle=(:word)")
    fun deleteWord(word:String)

    @Query("SELECT * from word ORDER BY wordTitle ASC")
    fun getAllWords(): LiveData<List<Word?>?>?

    @Query("SELECT * FROM word WHERE wordTitle=(:word) LIMIT 1")
    fun findWord(word: String?): Word?

    @Query("SELECT COUNT(*) from word")
    fun getCount():LiveData<Int>?

    @Query("SELECT * from word where isFav='true'")
    fun getAllFav():List<Word>
}