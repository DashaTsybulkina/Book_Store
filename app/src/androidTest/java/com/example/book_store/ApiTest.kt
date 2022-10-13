package com.example.book_store

import android.util.Log
import com.example.book_store.data.model.DetailBook
import com.example.book_store.network.BASE_URL
import com.example.book_store.network.BookService
import com.example.book_store.network.NewBooksResponse
import com.example.book_store.network.SearchBookResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.CountDownLatch

class ApiTest {
    lateinit var apiService: BookService

    @Before
    @Throws(Exception::class)
    fun createDb() {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        apiService =
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build().create(BookService::class.java)
    }

    @Test
    fun getNewBooks_Success() {
        val latch = CountDownLatch(1)
        var response:NewBooksResponse? = null
        GlobalScope.launch {
            try {
                response = apiService.getNewBooks()
                latch.countDown()
            }catch (e: Exception){
                Log.e("ApiTest", "failed getNewBooks")
            }
        }
        try {
            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        assert(response?.error=="0")
        assert(response?.books != null)
        assertTrue(response?.books?.size == 20)
    }

    @Test
    fun getSearchBook_Success() {
        val latch = CountDownLatch(1)
        var response:SearchBookResponse? = null
        GlobalScope.launch {
            try {
                response = apiService.getSearchBook("book", 1)
                latch.countDown()
            }catch (e: Exception){
                Log.e("ApiTest", "failed getNewBooks")
            }
        }
        try {
            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        assert(response?.error=="0")
        assert(response?.books != null)
    }

    @Test
    fun getBookInformation_Success() {
        val latch = CountDownLatch(1)
        var response:DetailBook? = null
        GlobalScope.launch {
            try {
                response = apiService.getBookInformation("1001591779911")
                latch.countDown()
            }catch (e: Exception){
                Log.e("ApiTest", "failed getNewBooks")
            }
        }
        try {
            latch.await()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        assert(response?.pages =="200")
    }
}