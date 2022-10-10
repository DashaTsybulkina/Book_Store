package com.example.book_store.ui.cart

import android.app.Application
import androidx.lifecycle.*
import com.example.book_store.data.BooksRepository
import com.example.book_store.data.model.DetailBook
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(val repository: BooksRepository ) : ViewModel() {
    private val _books = MutableLiveData<List<DetailBook>>()
    val books: LiveData<List<DetailBook>> = _books

    init {
        getBook()
    }

    fun getBook() {
        viewModelScope.launch {
            val listResult = repository.getBooksCart()
            if (listResult != null){
                _books.value = listResult!!
            }
        }
    }

    fun updateCount (book:DetailBook, position: Int, value:Int){
        viewModelScope.launch {
            repository.updateCount(book.isbn13, value)
            _books.value!!.get(position).count = value
        }
    }
}