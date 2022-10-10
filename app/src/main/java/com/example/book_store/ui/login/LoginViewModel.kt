package com.example.book_store.ui.login

import androidx.lifecycle.ViewModel
import com.example.book_store.data.BooksRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: BooksRepository) : ViewModel() {
    fun signInUser(email:String) {
        repository.correctUser(1,email, "ваш номер", "зарегистрированый", "пользователь")
    }
}