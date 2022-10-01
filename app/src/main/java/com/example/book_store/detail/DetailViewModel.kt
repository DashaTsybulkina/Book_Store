package com.example.book_store.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_store.model.DetailBook
import com.example.book_store.network.BookApi
import kotlinx.coroutines.launch

class DetailViewModel:ViewModel() {
    private val _book = MutableLiveData<DetailBook>()
    val book: LiveData<DetailBook> = _book

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    fun getBookInformation(isbn13: String) {
        viewModelScope.launch {
            try {
                val result = BookApi.retrofitService.getBookInformation(isbn13)
                Log.d("internet", "$result")
                _response.value = "Success: $result Mars properties retrieved"
                _book.value = result
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
                Log.d("internet", "no good")
            }
        }
    }
}