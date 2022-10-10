package com.example.book_store.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_store.data.BooksRepository
import com.example.book_store.data.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val repository: BooksRepository) : ViewModel() {
    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        getUser()
    }

    fun deleteUser(){
        repository.correctUser(-1, "noRegister@gmail.com", "+375000000000", "first", "last")
        getUser()
    }
    fun getUser() {
        _user.value = repository.getUser()
    }
}