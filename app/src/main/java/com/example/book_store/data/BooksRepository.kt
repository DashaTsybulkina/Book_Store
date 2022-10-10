package com.example.book_store.data

import android.util.Log
import com.example.book_store.data.model.Book
import com.example.book_store.data.model.DetailBook
import com.example.book_store.data.model.User
import com.example.book_store.database.BooksDao
import com.example.book_store.network.BookService
import com.example.book_store.network.NewBooksResponse
import com.example.book_store.network.SearchBookResponse
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksRepository @Inject constructor(
    private val booksDao: BooksDao,
    private val networkService: BookService
) {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private var user = User()



    suspend fun getBookInfo(isbn13: String): DetailBook =
        withContext(ioDispatcher) {
            var detailBook = booksDao.getDetailBook(isbn13)
            if (detailBook != null) {
                detailBook
            } else {
                try {
                    detailBook = networkService.getBookInformation(isbn13)
                    detailBook
                } catch (e: Exception) {
                    Log.e("TAG", "failed addCart : ${e.message}")
                }
            }
            return@withContext detailBook!!
        }

    suspend fun getNewBooks(): List<Book> =
        withContext(ioDispatcher) {
            var books: List<Book> = ArrayList()
            try {
                val response = networkService.getNewBooks()
                books = response.books
            } catch (e: Exception) {
                Log.e("TAG", "failed NewBooks : ${e.message}")
            }
            return@withContext books
        }

    suspend fun getSearchBook(query: String, page: Int): SearchBookResponse =
        withContext(ioDispatcher) {
            var response: SearchBookResponse? = null
            try {
                response = networkService.getSearchBook(query, page)
            } catch (e: Exception) {
                Log.e("TAG", "failed NewBooks : ${e.message}")
            }
            return@withContext response!!
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

    suspend fun getBooksCart(): List<DetailBook>? =
        withContext(ioDispatcher) {
            var books: List<DetailBook>? = null
            try {
                books = booksDao.getAllBooks()
            } catch (e: Exception) {
                Log.e(TAG, "failed getBook: ${e.message} ")
            }
            return@withContext books
        }

    suspend fun updateCount(isbn13: String, count: Int) {
        withContext(ioDispatcher) {
            try {
                booksDao.updateCount(isbn13, count)
            } catch (e: Exception) {
                Log.e("TAG", "failed update : ${e.message}")
            }
        }
    }

    fun correctUser(id:Int,email:String, phone: String, firstName:String, lastName:String){
        user.id = id
        user.email = email
        user.phone = phone
        user.firstName = firstName
        user.lastName = lastName
    }

    fun getUser():User{
        return user
    }

    companion object {
        private const val TAG = "BooksRepository"
    }

}