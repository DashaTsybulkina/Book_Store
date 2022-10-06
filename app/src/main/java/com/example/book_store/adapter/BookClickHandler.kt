package com.example.book_store.adapter

import com.example.book_store.data.model.Book

interface BookClickHandler {
    fun clickedBookItem(book: Book)
}