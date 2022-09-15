package com.example.book_store.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.book_store.R
import com.example.book_store.adapter.BookCatalogRecyclerAdapter
import com.example.book_store.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    val _viewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private var currentPage = 1
    lateinit var recyclerAdapter: BookCatalogRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            viewModel = _viewModel
        }
        recyclerAdapter = BookCatalogRecyclerAdapter()
        _binding!!.booksRecyclerView.layoutManager = LinearLayoutManager(activity)
        _binding!!.booksRecyclerView.adapter = recyclerAdapter


        _viewModel.books.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerAdapter.refreshUsers(it)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.search.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        setupRecyclerView()
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

            setOnQueryTextFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    showInputMethod(v.findFocus())
                }
            }
            onActionViewExpanded()
        }
    }

    private fun showInputMethod(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, 0)
    }

    private fun requestFirst(query: String) {
        currentPage = 1
        recyclerAdapter.clear()
        _viewModel.searchBook(query, currentPage)
    }

    private fun setupRecyclerView() {
        currentPage++
        _viewModel.searchBook(binding.search.query.toString(), currentPage)
    }

}