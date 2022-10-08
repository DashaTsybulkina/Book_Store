package com.example.book_store.ui.detail

import android.util.Log
import androidx.lifecycle.*
import com.example.book_store.data.BooksRepository
import com.example.book_store.data.model.DetailBook
import com.example.book_store.ui.catalog.BookApiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailViewModel@Inject constructor(val repository: BooksRepository) :ViewModel() {
    private var _book = MutableLiveData<DetailBook>()
    val book: LiveData<DetailBook> = _book

    private val _status = MutableLiveData<BookApiStatus>()
    val status: LiveData<BookApiStatus>
        get() = _status

    fun getBookInformation(isbn13: String){
        viewModelScope.launch {
            _status.value = BookApiStatus.LOADING
            try {
                val result = repository.getBookInfo(isbn13)
                Log.d("internet", "$result")
                _status.value = BookApiStatus.DONE
                _book.value = result
            } catch (e: Exception) {
                _status.value = BookApiStatus.ERROR
                Log.d("internet", "no good")
            }
        }
    }

    fun saveBook (){
        _book.value!!.count  = 1
        val book = _book.value
        if (book != null) {
            viewModelScope.launch {
                repository.addCart(book)
            }
        }
    }
}