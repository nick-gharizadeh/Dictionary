package com.example.hw14.model

import android.app.Application
import androidx.lifecycle.LiveData


class WordRepository internal constructor(application: Application?) {

    private var wordDao: WordDao? = null
    private var allWords: LiveData<List<Word?>?>? = null

    fun WordRepository(application: Application?) {
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



}