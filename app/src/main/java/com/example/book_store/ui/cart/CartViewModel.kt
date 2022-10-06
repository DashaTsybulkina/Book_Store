package com.example.book_store.ui.cart

import android.app.Application
import androidx.lifecycle.*
import com.example.book_store.data.BooksRepository
import com.example.book_store.data.model.DetailBook
import com.example.book_store.database.LocalDB
import kotlinx.coroutines.launch

class CartViewModel(val app: Application) : AndroidViewModel(app) {

    private val repository: BooksRepository

    private val _books = MutableLiveData<List<DetailBook>>()
    val books: LiveData<List<DetailBook>> = _books

    init {
        val bookDB = LocalDB.createDatabase(app).booksDao()
        repository = BooksRepository(bookDB)
        getBook()
    }

    fun getBook() {
        viewModelScope.launch {
            val listResult = repository.getBooksCart()
            _books.value = listResult
        }
    }
}