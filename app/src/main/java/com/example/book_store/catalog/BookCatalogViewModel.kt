package com.example.book_store.catalog

import androidx.lifecycle.*
import com.example.book_store.model.Book
import com.example.book_store.network.BookApi
import kotlinx.coroutines.launch

enum class BookApiStatus { LOADING, ERROR, DONE }

class BookCatalogViewModel : ViewModel() {

    private val _property = MutableLiveData<List<Book>>()
    val property: LiveData<List<Book>>
        get() = _property

    private val _status = MutableLiveData<BookApiStatus>()
    val status: LiveData<BookApiStatus>
        get() = _status

    init {
        _status.value = BookApiStatus.LOADING
        getBook()
    }

    private fun getBook() {
        viewModelScope.launch {
            _status.value = BookApiStatus.LOADING
            try {
                val listResult = BookApi.retrofitService.getNewBooks()
                _status.value = BookApiStatus.DONE
                _property.value = listResult.books
            } catch (e: Exception) {
                _status.value = BookApiStatus.ERROR
            }
        }
    }
}