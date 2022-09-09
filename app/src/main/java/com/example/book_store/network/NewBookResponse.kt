package com.example.book_store.network

import com.example.book_store.model.Book

data class NewBooksResponse(
    val error: String,
    val total: String,
//    val page:String,
    val books: List<Book>
)