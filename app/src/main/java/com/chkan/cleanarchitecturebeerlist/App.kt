package com.chkan.cleanarchitecturebeerlist

import android.app.Application
import com.chkan.cleanarchitecturebeerlist.data.di.DaggerAppComponent

class App: Application() {
    val appComponent = DaggerAppComponent.create()
}