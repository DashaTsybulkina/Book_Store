package com.example.book_store.di

import android.content.Context
import androidx.room.Room
import com.example.book_store.database.BooksDao
import com.example.book_store.database.BooksDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(private var context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideDatabase(context: Context): BooksDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BooksDatabase::class.java,
            "book.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideDBDao(db: BooksDatabase): BooksDao {
        return db.booksDao()
    }
}

@Module(includes = [DataBaseModule::class])
interface DBBindModule {
    @Binds
    fun bindDatabaseDao(databaseDao: BooksDao): BooksDao
}