package com.example.book_store.database

import android.content.Context
import androidx.room.Room

object LocalDB {
    fun createDatabase(context: Context): BooksDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BooksDatabase::class.java, "book.db"
        ).build()
    }
}