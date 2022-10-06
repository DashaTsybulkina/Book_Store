package com.example.book_store.data.model

import com.squareup.moshi.Json

data class Book(
    val title: String,
    val subtitle: String,
    val isbn13: String,
    val price: String,
    val image: String,
    @Json(name = "url")
    val link: String
    )