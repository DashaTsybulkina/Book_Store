package com.example.book_store.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.book_store.R
import com.example.book_store.adapter.BookCatalogRecyclerAdapter
import com.example.book_store.databinding.FragmentBookCatalogBinding
import com.example.book_store.databinding.FragmentSearchBinding
import com.example.book_store.model.Book

class BookCatalogFragment : Fragment() {

    val viewModel: BookCatalogViewModel by viewModels()
    private var binding: FragmentBookCatalogBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBookCatalogBinding.bind(view);

        val adapter = BookCatalogRecyclerAdapter()
        binding!!.listOfBooks.layoutManager = LinearLayoutManager(activity)
        binding!!.listOfBooks.adapter = adapter

        viewModel.property.observe(viewLifecycleOwner, Observer<List<Book>> {
            adapter.refreshUsers(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //  context?.getAppComponent()?.inject(this)
        return inflater.inflate(R.layout.fragment_book_catalog, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}