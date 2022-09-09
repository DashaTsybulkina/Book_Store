package com.example.book_store

import android.app.Application
import android.content.Context

//class App:Application() {
//    lateinit var appComponent: Component
//    override fun onCreate() {
//        appComponent = DaggerComponent.builder().build()
//        super.onCreate()
//    }
//}
//
//fun Context.getAppComponent(): Component{
//    return when(this){
//        is App -> appComponent
//        else -> (this.applicationContext as App).appComponent
//    }
//}