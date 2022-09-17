package com.example.book_store.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.book_store.model.DetailBook

class DetailViewModel:ViewModel() {
    private val _book = MutableLiveData<DetailBook>()
    val book: LiveData<DetailBook> = _book

    val memo = MutableLiveData<String>()
}