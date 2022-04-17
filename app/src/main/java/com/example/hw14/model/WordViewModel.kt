package com.example.hw14.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class WordViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val Repository: WordRepository
    val allWords: LiveData<List<Word?>?>?

    fun insert(word: Word?) {
        Repository.insert(word)
    }

    init {
        Repository = WordRepository(application)
        allWords = Repository.getAllWords()
    }
}