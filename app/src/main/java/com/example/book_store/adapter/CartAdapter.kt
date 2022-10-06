package com.example.book_store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book_store.R
import com.example.book_store.databinding.CartItemBinding
import com.example.book_store.model.DetailBook

class CartAdapter(): RecyclerView.Adapter<CartAdapter.CartViewHolder>()  {

    private var books: List<DetailBook> = ArrayList()


    inner class CartViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {
        val binding =
            CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {
        val book = books[position]
        setImage(holder.binding.imgBookImage, book.image)
        holder.binding!!.txtBookName.text = book.title
        holder.binding!!.txtBookPrice.text = book.price
        holder.binding!!.txtBookAuthor.text = book.authors
    }

    fun setImage(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView).load(it).placeholder(R.drawable.placeholder).into(imageView)
        }
    }

    override fun getItemCount() = books.size

    fun refreshUsers(books: List<DetailBook>) {
        this.books = books
        notifyDataSetChanged()
    }
}