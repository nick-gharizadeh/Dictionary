package com.example.hw14.model

import android.app.Application
import androidx.lifecycle.LiveData


class WordRepository(application: Application?) {

    private var wordDao: WordDao? = null
    private var allWords: LiveData<List<Word?>?>? = null

    init {
        wordRepository(application)
    }

    fun wordRepository(application: Application?) {
        val db = WordRoomDatabase.getDatabase(application!!)
        wordDao = db!!.wordDao()
        allWords = wordDao!!.getAllWords()
    }

    fun getAllWords(): LiveData<List<Word?>?>? {
        return allWords
    }

    fun insert(word: Word?) {
        wordDao?.insert(word)
    }

    fun findWord(word: String): Word? {
        return wordDao?.findWord(word)
    }

    fun deleteWord(word: String) {
        wordDao?.deleteWord(word)
    }

    fun update(word: Word?) {
        wordDao?.updateWord(word)
    }

    fun getCount():LiveData<Int>?
    {
       return wordDao?.getCount()
    }

    fun getAllFav():List<Word>?
    {
        return wordDao?.getAllFav()
    }

}