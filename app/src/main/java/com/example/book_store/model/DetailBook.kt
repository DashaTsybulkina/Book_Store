package com.example.book_store.model

import com.squareup.moshi.Json

data class DetailBook(
    val isbn13: String,
    val error: String,
    val title: String,
    val authors: String,
    val publisher: String,
    val language: String,
    val isbn10: String,
    val pages: String,
    val year: String,
    val rating: String,
    val desc: String,
    val price: String,
    val image: String,
    @Json(name = "url")
    val link: String,
    var isLiked: Boolean = false,
    var memo: String? = null,
    var disableHistory: Boolean = false
)