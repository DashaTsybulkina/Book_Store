package com.example.book_store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book_store.R
import com.example.book_store.databinding.CatalogBookItemBinding
import com.example.book_store.model.Book

class BookCatalogRecyclerAdapter() :
    RecyclerView.Adapter<BookCatalogRecyclerAdapter.BookCatalogViewHolder>() {

    private var books: List<Book> = ArrayList()

    class BookCatalogViewHolder(val binding: CatalogBookItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCatalogViewHolder {
        val binding =
            CatalogBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookCatalogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookCatalogViewHolder, position: Int) {
        val book = books[position]
        setImage(holder.binding.imgBookImage, book.image)
        holder.binding!!.txtBookName.text = book.title
        holder.binding!!.txtBookPrice.text = book.price
        holder.binding!!.txtBookAuthor.text = book.subtitle

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context, "gjkexbkjcm",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun setImage(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView).load(it).placeholder(R.drawable.placeholder).into(imageView)
        }
    }

    fun refreshUsers(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    override fun getItemCount() = books.size

    fun clear() {
     //   books.clear()
        notifyDataSetChanged()
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.isbn13 == newItem.isbn13
        }
    }
}