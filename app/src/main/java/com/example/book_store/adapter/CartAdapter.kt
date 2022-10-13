package com.example.book_store.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.NumberPicker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.book_store.R
import com.example.book_store.data.model.DetailBook
import com.example.book_store.databinding.CartItemBinding
import com.example.book_store.ui.cart.CartViewModel

class CartAdapter(private val viewModel: CartViewModel) :
    ListAdapter<DetailBook, CartAdapter.CartViewHolder>(BookDiffCallback()) {

    var books: List<DetailBook> = ArrayList()

    inner class CartViewHolder(val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(book:DetailBook){
                setImage(binding.imgBookImage, book.image)
                binding.txtBookName.text = book.title
                binding.txtBookPrice.text = book.price
                binding.txtBookAuthor.text = book.authors
                binding.count.text = book.count.toString()
            }
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
        val book = books.get(position)
        holder.bind(book)

        holder.binding.count.setOnClickListener {
            val picker = NumberPicker(holder.binding.imgBookImage.context)
            picker.minValue = 1
            picker.maxValue = 100
            val builder = AlertDialog.Builder(holder.binding.imgBookImage.context)
            builder.setView(picker)
            builder.setTitle("Changing the count")
            builder.setMessage("Choose a value :")
            builder.setPositiveButton(
                "OK"
            ) { dialog, id ->
                dialog.cancel()
                holder.binding.count.text = picker.value.toString()
                viewModel.updateCount(books[position], position, picker.value)
            }
            builder.create().show()
        }

        holder.binding.deleteItem.setOnClickListener{
            viewModel.deleteItem(books[position], position)
        }
    }

    fun setImage(imageView: ImageView, url: String?) {
        url?.let {
            Glide.with(imageView).load(it).placeholder(R.drawable.placeholder).into(imageView)
        }
    }

    override fun getItemCount(): Int = books.size

    fun refreshUsers(books: List<DetailBook>) {
        this.books = books
        notifyDataSetChanged()
    }
}

class BookDiffCallback : DiffUtil.ItemCallback<DetailBook>() {

    override fun areItemsTheSame(oldItem: DetailBook, newItem: DetailBook): Boolean {
        return oldItem.isbn13== newItem.isbn13
    }


    override fun areContentsTheSame(oldItem: DetailBook, newItem: DetailBook): Boolean {
        return oldItem == newItem
    }


}