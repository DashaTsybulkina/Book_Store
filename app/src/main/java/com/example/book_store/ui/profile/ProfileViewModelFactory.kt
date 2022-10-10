package com.example.book_store.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.book_store.ui.detail.DetailViewModel
import javax.inject.Inject
import javax.inject.Provider

class ProfileViewModelFactory @Inject constructor(
    myViewModelProvider: Provider<ProfileViewModel>
) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        ProfileViewModel::class.java to myViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}