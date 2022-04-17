package com.example.hw14.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val wordTitle: String,
    @ColumnInfo val meaning: String,
    @ColumnInfo val example: String,
    @ColumnInfo val synonyms: String,
)
