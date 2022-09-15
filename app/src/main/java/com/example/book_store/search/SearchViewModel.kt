package com.example.book_store.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_store.model.Book
import com.example.book_store.network.BookApi
import com.example.book_store.network.SearchBookResponse
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val books = MutableLiveData<MutableList<Book>>()
    private val bookList = ArrayList<Book>()
    private val isLastPage = MutableLiveData<Boolean>()

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    private var showNoData = MutableLiveData<Boolean>()

    fun searchBook(query: String, page: Int) {
        if (page == 1) {
            bookList.clear()
        }
        viewModelScope.launch {
            try {
                val result = BookApi.retrofitService.getSearchBook(query, page)
                if (result.page != null && result.totalNum != null) {
                    isLastPage.value =
                        result.page.toInt().times(10) * 10 >= result.totalNum.toInt()
                }
                bookList.addAll(result.books)
                books.value = bookList
                _response.value = "Success!"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }

    }

    private fun invalidateShowNoData() {
        showNoData.value = books.value == null || books.value!!.isEmpty()
    }
}