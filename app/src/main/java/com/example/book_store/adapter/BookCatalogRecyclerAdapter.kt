package com.example.book_store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.book_store.databinding.CatalogBookItemBinding
import com.example.book_store.model.Book
import kotlinx.android.synthetic.main.catalog_book_item.view.*

class BookCatalogRecyclerAdapter() :
    RecyclerView.Adapter<BookCatalogRecyclerAdapter.BookCatalogViewHolder>() {

    private var books: List<Book> = ArrayList()

    class BookCatalogViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding: CatalogBookItemBinding? = DataBindingUtil.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCatalogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BookCatalogViewHolder(CatalogBookItemBinding.inflate(layoutInflater,parent,false).root)
    }

    override fun onBindViewHolder(holder: BookCatalogViewHolder, position: Int) {
        val book = books[position]
        holder.binding?.property = book
    }

    fun refreshUsers(books: List<Book>) {
        this.books =books
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return books.size
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