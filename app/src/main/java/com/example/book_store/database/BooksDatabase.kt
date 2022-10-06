package com.example.book_store.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.book_store.data.model.DetailBook

@Database(entities = [DetailBook::class], version = 1)
abstract class BooksDatabase : RoomDatabase()  {
    abstract fun booksDao(): BooksDao
}