package com.chkan.cleanarchitecturebeerlist.data.di

import com.chkan.cleanarchitecturebeerlist.features.beers.ui.MainActivity
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}