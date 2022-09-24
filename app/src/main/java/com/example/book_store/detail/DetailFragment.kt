package com.example.book_store.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.book_store.R
import com.example.book_store.databinding.FragmentDetailBinding

private const val ARG_TITLE = "title"

class DetailFragment : Fragment() {
    private var param: String? = null
    private var _binding: FragmentDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getString(ARG_TITLE)
            param?.let { it1 -> Log.d("DETAIL", it1) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}