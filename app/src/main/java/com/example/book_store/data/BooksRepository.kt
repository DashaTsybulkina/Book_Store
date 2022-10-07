package com.example.book_store.data

import android.util.Log
import com.example.book_store.data.model.DetailBook
import com.example.book_store.database.BooksDao
import com.example.book_store.network.BookApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksRepository(
    private val booksDao: BooksDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getBookInfo(isbn13: String): DetailBook =
        withContext(ioDispatcher) {
            var detailBook = booksDao.getDetailBook(isbn13)
            if (detailBook != null) {
                detailBook
            } else {
                try {
                    detailBook = BookApi.retrofitService.getBookInformation(isbn13)
                    detailBook
                } catch (e: Exception) {
                    Log.e("TAG", "failed addCart : ${e.message}")
                }
            }
            return@withContext detailBook!!
        }

    suspend fun addCart(detailBook: DetailBook) {
        withContext(ioDispatcher) {
            try {
                booksDao.insert(detailBook)
            } catch (e: Exception) {
                Log.e("TAG", "failed addCart : ${e.message}")
            }
        }
    }

    suspend fun getBooksCart(): List<DetailBook>?  =
        withContext(ioDispatcher) {
            var books:List<DetailBook>? = null
            try {
                books = booksDao.getAllBooks()
            } catch (e: Exception) {
                Log.e(TAG, "failed getBook: ${e.message} ")
            }
            return@withContext books
        }

    suspend fun updateCount(isbn13: String, count:Int){
        withContext(ioDispatcher){
            try {
                booksDao.updateCount(isbn13, count)
            }catch (e: Exception) {
                Log.e("TAG", "failed update : ${e.message}")
            }
        }
    }

    companion object {
        private const val TAG = "BooksRepository"
    }

}