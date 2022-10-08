package com.example.book_store.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.book_store.data.model.DetailBook

@Database(entities = [DetailBook::class], version = 1)
abstract class BooksDatabase : RoomDatabase()  {
    abstract fun booksDao(): BooksDao

    companion object {
        @Volatile
        private var INSTANCE: BooksDatabase? = null

        fun getInstance(context: Context): BooksDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BooksDatabase::class.java,
                        "book.db"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}