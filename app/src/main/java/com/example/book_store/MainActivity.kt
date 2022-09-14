package com.example.book_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.book_store.catalog.BookCatalogFragment
import com.example.book_store.databinding.CatalogBookItemBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.bookCatalogFragment,
            R.id.searchFragment,
            R.id.cartFragment,
            R.id.profileFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        fragmentTransaction.add(R.id.container, BookCatalogFragment.newInstance())
//        fragmentTransaction.commit()
    }
}