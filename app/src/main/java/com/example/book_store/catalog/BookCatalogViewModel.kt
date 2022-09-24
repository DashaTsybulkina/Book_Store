package com.example.book_store.catalog

import android.util.Log
import androidx.lifecycle.*
import com.example.book_store.model.Book
import com.example.book_store.network.BookApi
import kotlinx.coroutines.launch

class BookCatalogViewModel : ViewModel() {

    private val _navigateToSelectedProperty = MutableLiveData<Book>()
    val navigateToSelectedProperty: LiveData<Book>
        get() = _navigateToSelectedProperty

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
                Log.d("INTERHET", BookApi.retrofitService.toString())
                val listResult = BookApi.retrofitService.getNewBooks()
                Log.d("INTERHET", "good")
                _response.value = "Success: $listResult Mars properties retrieved"
                _property.value = listResult.books
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.d("INTERHET", "no")
            }
        }
    }

    fun displayPropertyDetails(book: Book) {
        _navigateToSelectedProperty.value = book
    }
}