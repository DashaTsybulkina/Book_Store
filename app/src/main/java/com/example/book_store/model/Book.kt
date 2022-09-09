package com.example.book_store.model

import com.squareup.moshi.Json
import org.jetbrains.annotations.NotNull

data class Book(
    val isbn13: String,
    val title: String,
    val subtitle: String,
    val price: String,
    val image: String,
    @Json(name = "url")
    val link: String)