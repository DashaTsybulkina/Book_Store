package com.example.book_store.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.book_store.data.BooksRepository
import com.example.book_store.database.LocalDB
import com.example.book_store.model.DetailBook
import com.example.book_store.network.BookApi
import kotlinx.coroutines.launch

class DetailViewModel(val app:Application):AndroidViewModel(app) {
    private val _book = MutableLiveData<DetailBook>()
    val book: LiveData<DetailBook> = _book

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private val repository: BooksRepository

    init{
        val bookDB = LocalDB.createDatabase(app).booksDao()
        repository = BooksRepository(bookDB)
    }

    fun getBookInformation(isbn13: String){
        viewModelScope.launch {
            try {
                val result = repository.getBookInfo(isbn13)
                Log.d("internet", "$result")
                _response.value = "Success: $result Mars properties retrieved"
                _book.value = result
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.d("internet", "no good")
            }
        }
    }

    fun saveBook (){
        val book = _book.value
        if (book != null) {
            viewModelScope.launch {
                repository.addCart(book)
            }
        }
    }
}