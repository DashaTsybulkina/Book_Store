package com.example.book_store.ui.registration

import androidx.lifecycle.ViewModel
import com.example.book_store.data.BooksRepository
import com.example.book_store.data.model.User
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(val repository: BooksRepository) : ViewModel() {
    fun registeringUser(email:String, phone:String, firstName:String, lastName:String) {
        repository.correctUser(1,email, phone, firstName, lastName)
    }
}