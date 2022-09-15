package com.example.book_store.network

import com.example.book_store.model.Book

class SearchBookResponse (
    val error: String,
    val totalNum: String? = null,
    val page: String? = null,
    val books: List<Book>
)