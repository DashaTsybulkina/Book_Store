package com.example.book_store.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.book_store.ui.catalog.BookCatalogViewModel
import javax.inject.Inject
import javax.inject.Provider

class SearchViewModelFactory @Inject constructor(
    myViewModelProvider: Provider<SearchViewModel>
) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        SearchViewModel::class.java to myViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}