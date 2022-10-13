package com.example.book_store.ui.cart

import android.app.Application
import androidx.lifecycle.*
import com.example.book_store.data.BooksRepository
import com.example.book_store.data.model.DetailBook
import com.example.book_store.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

class CartViewModel @Inject constructor(val repository: BooksRepository ) : ViewModel() {
    private val _books = MutableLiveData<List<DetailBook>>()
    val books: LiveData<List<DetailBook>> = _books

    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    init {
        getBook()
        getUser()
    }

    fun getUser() {
        _user.value = repository.getUser()
    }

    fun getBook() {
        viewModelScope.launch {
            val listResult = repository.getBooksCart()
            if (listResult != null){
                setBooks(listResult)
            }
        }
    }

    fun getSum():Double{
        var sum = 0.0
        for (book in _books.value!!) {
            sum += book.count * book.price.substring(1).toFloat()
        }
        return (sum * 100.0).roundToInt().toFloat() / 100.0
    }

    fun updateCount (book:DetailBook, position: Int, value:Int){
        viewModelScope.launch {
            repository.updateCount(book.isbn13, value)
            getBook()
        }
    }

    fun deleteItem(book:DetailBook, position: Int){
        viewModelScope.launch {
            repository.deleteBook(book.isbn13)
            getBook()
        }
    }

    fun setBooks(books:List<DetailBook>){
        _books.value = books
    }
}