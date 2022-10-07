package com.example.book_store.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.book_store.R
import com.example.book_store.adapter.CartAdapter
import com.example.book_store.data.model.DetailBook
import com.example.book_store.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    val viewModel: CartViewModel by viewModels()
    private var binding: FragmentCartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCartBinding.bind(view);

        val adapter = CartAdapter(viewModel)
        binding!!.booksCart.layoutManager = LinearLayoutManager(activity)
        binding!!.booksCart.adapter = adapter

        viewModel.books.observe(viewLifecycleOwner, Observer<List<DetailBook>> {
            adapter.refreshUsers(it)
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBook()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}