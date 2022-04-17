package com.example.hw14.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao?

    companion object {
        private var INSTANCE: WordRoomDatabase? = null
        fun getDatabase(context: Context): WordRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(WordRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WordRoomDatabase::class.java, "word_database"
                        ).allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}