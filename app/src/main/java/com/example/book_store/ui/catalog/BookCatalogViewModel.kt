package com.example.book_store.ui.catalog

import androidx.lifecycle.*
import com.example.book_store.data.BooksRepository
import com.example.book_store.data.model.Book
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class BookApiStatus { LOADING, ERROR, DONE }

class BookCatalogViewModel @Inject constructor( val repository: BooksRepository) : ViewModel() {

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
                val listResult = repository.getNewBooks()
                _status.value = BookApiStatus.DONE
                _property.value = listResult
            } catch (e: Exception) {
                _status.value = BookApiStatus.ERROR
            }
        }
    }
}