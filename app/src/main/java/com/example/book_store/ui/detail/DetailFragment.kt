package com.example.book_store.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.book_store.R
import com.example.book_store.data.model.DetailBook
import com.example.book_store.databinding.FragmentDetailBinding
import com.example.book_store.getAppComponent
import com.example.book_store.ui.catalog.BookApiStatus
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val ARG_TITLE = "isbn13"

class DetailFragment : Fragment() {
    lateinit var isbn13: String
    private var _binding: FragmentDetailBinding? = null
    val _viewModel: DetailViewModel by viewModels{context?.getAppComponent()!!.detailFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isbn13 = it.getString(ARG_TITLE).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel.getBookInformation(isbn13)
        val nav = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        nav.visibility = View.GONE
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val nav = requireActivity().findViewById<BottomNavigationView>(R.id.nav_view)
        nav.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view);
        _viewModel.book.observe(viewLifecycleOwner, Observer<DetailBook> {
            setImage(_binding!!.imgFragmentBook, it.image)
            _binding!!.txtDetailName.text = it.title
            _binding!!.txtDetailAuthor.text = it.authors
            _binding!!.txtDetailPrice.text = it.price
            _binding!!.txtDetailAboutBook.text = it.desc
            _binding!!.textLink.text = it.link
            if(it.isAddCart){
                _binding!!.buttonDetailBuy.text = getString(R.string.in_cart)
            }
        })


        _binding!!.textLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(_viewModel.book.value?.link))
            startActivity(intent)
        }

        _viewModel.status.observe(viewLifecycleOwner, Observer<BookApiStatus> {
            when(it){
                BookApiStatus.LOADING-> {
                    _binding!!.statusImage.visibility = View.VISIBLE
                    _binding!!.statusImage.setImageResource(R.drawable.loading_animation)
                }
                BookApiStatus.ERROR -> {
                    _binding!!.statusImage.visibility = View.VISIBLE
                    _binding!!.statusImage.setImageResource(R.drawable.ic_connection_error)
                }
                BookApiStatus.DONE -> {
                    _binding!!.statusImage.visibility = View.GONE
                }
            }
        })

        _binding!!.buttonDetailBuy.setOnClickListener {
            _viewModel.saveBook()
            Toast.makeText(context, "Book added to cart", Toast.LENGTH_SHORT).show()
            _binding!!.buttonDetailBuy.text = getString(R.string.in_cart)
        }
    }

    fun setImage(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView).load(it).placeholder(R.drawable.placeholder).into(imageView)
        }
    }
}