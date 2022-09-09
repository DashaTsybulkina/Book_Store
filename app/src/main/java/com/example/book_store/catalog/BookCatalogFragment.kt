package com.example.book_store.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.book_store.R
import com.example.book_store.adapter.BookCatalogRecyclerAdapter
import com.example.book_store.model.Book
import kotlinx.android.synthetic.main.fragment_book_catalog.*

class BookCatalogFragment : Fragment() {
    val bookList = arrayListOf<Book>()

   lateinit var viewModel: BookCatalogViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BookCatalogRecyclerAdapter()
        val ListOfBooks = requireView().findViewById<RecyclerView>(R.id.list_of_books)
        ListOfBooks.layoutManager = LinearLayoutManager(activity)
        ListOfBooks.adapter = adapter

        viewModel.property.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.refreshUsers(it)
            }
        })

      //  textView.text = viewModel.response.value
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_book_catalog, container, false)

        viewModel = ViewModelProvider(this)[BookCatalogViewModel::class.java]
      //  context?.getAppComponent()?.inject(this)
        return view
    }

    companion object {
        fun newInstance() =
            BookCatalogFragment()
    }
}