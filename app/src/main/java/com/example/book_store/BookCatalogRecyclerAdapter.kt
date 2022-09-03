package com.example.book_store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.book_store.model.Book
import kotlinx.android.synthetic.main.catalog_book_item.view.*

class BookCatalogRecyclerAdapter(private val itemList: List<Book>) :
    RecyclerView.Adapter<BookCatalogRecyclerAdapter.BookCatalogViewHolder>() {
    class BookCatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookCatalogViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.catalog_book_item, parent, false)
        return BookCatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookCatalogViewHolder, position: Int) {
        val book = itemList[position]
        holder.itemView.txtBookName.text = book.bookName
        holder.itemView.txtBookAuthor.text = book.bookAuthor
        holder.itemView.txtBookPrice.text = book.bookPrice
        holder.itemView.txtBookRating.text = book.bookRating
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}