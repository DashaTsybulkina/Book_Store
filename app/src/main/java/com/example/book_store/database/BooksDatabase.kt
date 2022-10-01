package com.example.book_store.database

import androidx.room.Database
import com.example.book_store.model.DetailBook

@Database(entities = [DetailBook::class], version = 1)
abstract class BooksDatabase {
    abstract fun booksDao(): BooksDao
}