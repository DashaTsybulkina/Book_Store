package com.example.book_store

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.book_store.data.model.DetailBook
import com.example.book_store.database.BooksDao
import com.example.book_store.database.BooksDatabase
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BookDaoTest {

    lateinit var booksDao: BooksDao
    lateinit var db: BooksDatabase

    @Before
    @Throws(Exception::class)
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            BooksDatabase::class.java
        )
            .build()
        booksDao = db.booksDao()
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun insertBook() {
        val employees = getBook()
        booksDao.insert(employees)
        val dbEmployees: List<DetailBook> = booksDao.getAllBooks()
        assertEquals(1, dbEmployees.size)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun updateBookCount() {
        var detailBook = getBook()
        booksDao.insert(detailBook)
        booksDao.updateCount(detailBook.isbn13, 100)
        val dbBook= booksDao.getDetailBook(detailBook.isbn13)
        assertEquals(100, dbBook!!.count)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun deleteAllBooks() {
        var detailBook = getBook()
        booksDao.insert(detailBook)
        booksDao.deleteBooks()
        assertTrue(booksDao.getAllBooks().isEmpty())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun deleteOneBooks() {
        var detailBook = getBook()
        booksDao.insert(detailBook)
        detailBook.isbn13 = "1"
        booksDao.insert(detailBook)
        booksDao.deleteBook("1")
        assertEquals(booksDao.getAllBooks().size, 1)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun getOneBook() {
        var detailBook = getBook()
        booksDao.insert(detailBook)
        detailBook.isbn13 = "1"
        detailBook.price = "12"
        booksDao.insert(detailBook)
        val dbBook = booksDao.getDetailBook("1")
        assertEquals(dbBook!!.price, "12")
    }

    @After
    @Throws(java.lang.Exception::class)
    fun closeDb() {
        db.close()
    }

    fun getBook(): DetailBook {
        return DetailBook(
            "1515",
            "name",
            "bookTitle",
            "bah",
            "lol",
            "russian",
            "123",
            "123",
            "1981",
            "7.9",
            "qaclkndkv",
            "123",
            "https://picsum.photos/id/237/200/300",
            "https://picsum.photos",
        )

    }
}