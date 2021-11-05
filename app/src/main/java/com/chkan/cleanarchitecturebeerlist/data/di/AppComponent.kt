package com.chkan.cleanarchitecturebeerlist.data.di

import com.chkan.cleanarchitecturebeerlist.features.beers.vm.ViewModelFactory
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {
    fun getViewModelFactory(): ViewModelFactory
}
