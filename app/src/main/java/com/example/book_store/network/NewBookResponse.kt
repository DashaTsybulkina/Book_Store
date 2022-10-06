package com.example.book_store.network

import com.example.book_store.data.model.Book

data class NewBooksResponse(
    val error: String,
    val total: String,
    val books: List<Book>
)