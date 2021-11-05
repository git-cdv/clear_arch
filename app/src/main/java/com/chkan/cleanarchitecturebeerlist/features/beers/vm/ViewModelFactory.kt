package com.chkan.cleanarchitecturebeerlist.features.beers.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chkan.cleanarchitecturebeerlist.features.beers.domain.usecase.GetBeersUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor (private val getBeersUseCase: GetBeersUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeersViewModel(getBeersUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}