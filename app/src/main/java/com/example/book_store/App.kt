package com.example.book_store

import android.app.Application
import android.content.Context
import com.example.book_store.di.DaggerDatabaseComponent
import com.example.book_store.di.DataBaseModule
import com.example.book_store.di.DatabaseComponent

class App:Application() {
    lateinit var daggerComponent: DatabaseComponent
    override fun onCreate() {
        super.onCreate()
        daggerComponent =
            DaggerDatabaseComponent.builder().dataBaseModule(DataBaseModule(this)).build()
    }
}

fun Context.getAppComponent(): DatabaseComponent {
    return when (this) {
        is App -> daggerComponent
        else -> (this.applicationContext as App).daggerComponent
    }
}