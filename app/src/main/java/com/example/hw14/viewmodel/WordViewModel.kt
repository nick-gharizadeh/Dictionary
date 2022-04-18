package com.example.hw14.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.hw14.model.Word
import com.example.hw14.model.WordRepository


class WordViewModel(application: Application) : AndroidViewModel(application) {
    val Repository: WordRepository
    val allWords: LiveData<List<Word?>?>?
    var countLiveData:LiveData<Int>?
    var selectedWord: Word? = null


    init {
        Repository = WordRepository(application)
        allWords = Repository.getAllWords()
        countLiveData = Repository.getCount()
    }

    fun insert(word: Word?) {
        Repository.insert(word)
    }

    fun findWord(word: String): Word? {
        return Repository.findWord(word)
    }

    fun deleteWord(word: String) {
        Repository.deleteWord(word)
    }

    fun updateWord(word: Word?) {
        Repository.update(word)
    }


}