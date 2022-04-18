package com.example.hw14.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.hw14.model.Word
import com.example.hw14.model.WordRepository


class WordViewModel(application: Application) : AndroidViewModel(application) {
    val Repository: WordRepository
    val allWords: LiveData<List<Word?>?>?
    var selectedWord :Word?=null
    init {
        Repository = WordRepository(application)
        allWords = Repository.getAllWords()
    }
    fun insert(word: Word?) {
        Repository.insert(word)
    }

    fun findWord(word: String): Word? {
        return Repository.findWord(word)
    }

}