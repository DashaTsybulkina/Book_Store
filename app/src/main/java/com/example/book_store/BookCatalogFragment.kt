package com.example.book_store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.book_store.model.Book
import kotlinx.android.synthetic.main.fragment_book_catalog.*

class BookCatalogFragment : Fragment() {
    val bookList = arrayListOf<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_catalog, container, false)
        val adapter = BookCatalogRecyclerAdapter(bookList)
        val ListOfBooks = view.findViewById<RecyclerView>(R.id.list_of_books)
        ListOfBooks.layoutManager = LinearLayoutManager(activity)
        ListOfBooks.adapter = adapter
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            BookCatalogFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}