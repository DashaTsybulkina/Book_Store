package com.example.book_store.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "detailBook")
data class DetailBook(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "isbn13")
    var isbn13: String,
    @ColumnInfo(name = "error")
    val error: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "authors")
    val authors: String,
    @ColumnInfo(name = "publisher")
    val publisher: String,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "isbn10")
    val isbn10: String,
    @ColumnInfo(name = "pages")
    val pages: String,
    @ColumnInfo(name = "year")
    val year: String,
    @ColumnInfo(name = "rating")
    val rating: String,
    @ColumnInfo(name = "desc")
    val desc: String,
    @ColumnInfo(name = "price")
    var price: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "link")
    @Json(name = "url")
    val link: String,
    @ColumnInfo(name = "isAddCart")
    var isAddCart: Boolean = false,
    @ColumnInfo(name = "count")
    var count: Int = 0,
)