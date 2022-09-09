package com.example.book_store.catalog

import androidx.lifecycle.*
import com.example.book_store.model.Book
import com.example.book_store.network.BookApi
import com.example.book_store.network.NewBooksResponse
import kotlinx.coroutines.launch

class BookCatalogViewModel : ViewModel() {

    private val _property = MutableLiveData<List<Book>>()

    val property: LiveData<List<Book>>
        get() = _property

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getBook()
    }

    fun getBook() {
        viewModelScope.launch {
            try {
                val listResult = BookApi.retrofitService.getNewBooks()
                _response.value = "Success: ${listResult} Mars properties retrieved"
                _property.value = listResult.books
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}