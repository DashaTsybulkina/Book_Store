package com.example.book_store.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.book_store.data.model.DetailBook

@Dao
interface BooksDao  {
    @Query("DELETE FROM detailBook")
    fun deleteBooks()

    @Query("DELETE FROM detailBook WHERE isbn13 = :isbn13")
    fun deleteBook(isbn13: String?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: DetailBook?)

    @Query("SELECT * FROM detailBook WHERE isbn13 = :isbn13")
    fun getDetailBook(isbn13: String?): DetailBook?

    @Query("SELECT * FROM detailBook")
    fun getAllBooks():List<DetailBook>

    @Query("UPDATE detailBook SET count = :amount  WHERE isbn13 = :isbn13")
    fun updateCount(isbn13: String?, amount:Int)
}