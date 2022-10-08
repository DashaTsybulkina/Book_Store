package com.example.book_store.di

import com.example.book_store.network.BASE_URL
import com.example.book_store.network.BookService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
        @Singleton
        @Provides
        fun getRetrofit(MoshiFactory: Moshi): BookService {
            return Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(MoshiFactory))
                .baseUrl(BASE_URL)
                .build().create(BookService::class.java)
        }

        @Provides
        fun getMoshiConverterFactory(): Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
}
