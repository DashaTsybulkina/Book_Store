package com.example.book_store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.book_store.R
import com.example.book_store.model.Book
import kotlinx.android.synthetic.main.catalog_book_item.view.*

class BookCatalogRecyclerAdapter() :
    RecyclerView.Adapter<BookCatalogRecyclerAdapter.BookCatalogViewHolder>() {

    private var books: List<Book> = ArrayList()

    class BookCatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCatalogViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.catalog_book_item, parent, false)
        return BookCatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookCatalogViewHolder, position: Int) {
        val book = books[position]
//        holder.itemView.txtBookName.text = book.title
//        holder.itemView.txtBookAuthor.text = book.subtitle
//        holder.itemView.txtBookPrice.text = book.price
    }

    fun refreshUsers(books: List<Book>) {
        this.books =books
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return books.size
    }
}