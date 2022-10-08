package com.example.book_store.network

import com.example.book_store.data.model.DetailBook
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_VERSION = "1.0"
const val BASE_URL = "https://api.itbook.store/$API_VERSION/"

interface BookService {
    @GET("new")
    suspend fun getNewBooks(): NewBooksResponse

    @GET("search/{query}/{page}")
    suspend fun getSearchBook(
        @Path("query") query: String,
        @Path("page") page: Int
    ): SearchBookResponse

    @GET("books/{isbn13}")
    suspend fun getBookInformation(@Path("isbn13") isbn13: String): DetailBook
}
