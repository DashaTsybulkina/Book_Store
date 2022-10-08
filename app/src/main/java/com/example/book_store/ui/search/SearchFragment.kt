package com.example.book_store.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_store.R
import com.example.book_store.adapter.BookCatalogRecyclerAdapter
import com.example.book_store.adapter.BookClickHandler
import com.example.book_store.data.model.Book
import com.example.book_store.databinding.FragmentSearchBinding
import com.example.book_store.getAppComponent
import com.example.book_store.ui.catalog.BookApiStatus

class SearchFragment : Fragment(), BookClickHandler {
    val viewModel: SearchViewModel by viewModels{context?.getAppComponent()!!.searchFactory() }
    private var binding: FragmentSearchBinding? = null

    private var currentPage = 1
    lateinit var recyclerAdapter: BookCatalogRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)

        recyclerAdapter = BookCatalogRecyclerAdapter(this)
        binding!!.booksRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding!!.booksRecyclerView.adapter = recyclerAdapter

        viewModel.books.observe(viewLifecycleOwner, Observer<List<Book>> {
            it?.let {
                recyclerAdapter.refreshUsers(it)
            }
        })

        binding!!.search.apply {

            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        requestFirst(query)
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

        viewModel.status.observe(viewLifecycleOwner, Observer<BookApiStatus>{
            when(it){
                BookApiStatus.LOADING-> {
                    binding!!.statusImage.visibility = View.VISIBLE
                    binding!!.statusImage.setImageResource(R.drawable.loading_animation)
                }
                BookApiStatus.ERROR -> {
                    binding!!.statusImage.visibility = View.VISIBLE
                    binding!!.statusImage.setImageResource(R.drawable.ic_connection_error)
                }
                BookApiStatus.DONE -> {
                    binding!!.statusImage.visibility = View.GONE
                }
                else -> {
                    binding!!.statusImage.visibility = View.GONE
                }
            }
        })
    }

    private fun showInputMethod(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun requestFirst(query: String) {
        currentPage = 1
        recyclerAdapter.clear()
        viewModel.searchBook(query, currentPage)
    }

    override fun clickedBookItem(book: Book) {
        val bundle = bundleOf("isbn13" to book.isbn13)
        findNavController().navigate(R.id.detailFragment, bundle)
    }
}