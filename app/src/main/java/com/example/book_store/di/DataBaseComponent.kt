package com.example.book_store.di

import com.example.book_store.ui.cart.CartFragment
import com.example.book_store.ui.cart.CartViewModelFactory
import com.example.book_store.ui.catalog.BookCatalogFragment
import com.example.book_store.ui.catalog.BookCatalogViewModelFactory
import com.example.book_store.ui.detail.DetailFragment
import com.example.book_store.ui.detail.DetailViewModelFactory
import com.example.book_store.ui.search.SearchFragment
import com.example.book_store.ui.search.SearchViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataBaseModule::class, NetworkModule::class])
@Singleton
interface DatabaseComponent {
    fun cartFactory(): CartViewModelFactory
    fun catalogFactory(): BookCatalogViewModelFactory
    fun searchFactory() : SearchViewModelFactory
    fun detailFactory() : DetailViewModelFactory
//    fun inject(fragment: CartFragment)
//    fun inject(fragment: BookCatalogFragment)
//    fun inject(fragment: DetailFragment)
//    fun inject(fragment: SearchFragment)
}