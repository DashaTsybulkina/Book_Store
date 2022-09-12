package com.example.book_store

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:image")
fun setImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView).load(it).placeholder(R.drawable.placeholder).into(imageView)
    }
}