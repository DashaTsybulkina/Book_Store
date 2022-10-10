package com.example.book_store.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.book_store.ui.registration.RegistrationViewModel
import javax.inject.Inject
import javax.inject.Provider

class LoginViewModelFactory @Inject constructor(
    myViewModelProvider: Provider<LoginViewModel>
) : ViewModelProvider.Factory {
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        LoginViewModel::class.java to myViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}