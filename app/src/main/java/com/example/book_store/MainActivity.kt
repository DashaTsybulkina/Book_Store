package com.example.book_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.book_store.catalog.BookCatalogFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.container, BookCatalogFragment.newInstance())
        fragmentTransaction.commit()
    }
}