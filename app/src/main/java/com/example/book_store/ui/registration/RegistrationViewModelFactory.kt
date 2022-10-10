package com.example.book_store.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.book_store.ui.profile.ProfileViewModel
import javax.inject.Inject
import javax.inject.Provider

class RegistrationViewModelFactory @Inject constructor(
    myViewModelProvider: Provider<RegistrationViewModel>
) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        RegistrationViewModel::class.java to myViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}