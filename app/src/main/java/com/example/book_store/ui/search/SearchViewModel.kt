package com.example.book_store.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_store.data.model.Book
import com.example.book_store.network.BookApi
import com.example.book_store.ui.catalog.BookApiStatus
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    val books = MutableLiveData<MutableList<Book>>()
    private val bookList = ArrayList<Book>()
    private val isLastPage = MutableLiveData<Boolean>()

    private val _status = MutableLiveData<BookApiStatus>()
    val status: LiveData<BookApiStatus>
        get() = _status

    private var showNoData = MutableLiveData<Boolean>()

    fun searchBook(query: String, page: Int) {
        if (page == 1) {
            bookList.clear()
        }
        viewModelScope.launch {
            try {
                _status.value = BookApiStatus.LOADING
                val result = BookApi.retrofitService.getSearchBook(query, page)
                if (result.page != null && result.totalNum != null) {
                    isLastPage.value =
                        result.page.toInt().times(10) * 10 >= result.totalNum.toInt()
                }
                bookList.addAll(result.books)
                books.value = bookList
                _status.value = BookApiStatus.DONE
            } catch (e: Exception) {
                _status.value = BookApiStatus.ERROR
            }
        }

    }

    private fun invalidateShowNoData() {
        showNoData.value = books.value == null || books.value!!.isEmpty()
    }
}