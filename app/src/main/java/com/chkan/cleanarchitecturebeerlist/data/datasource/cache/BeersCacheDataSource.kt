package com.chkan.cleanarchitecturebeerlist.data.datasource.cache

import com.chkan.cleanarchitecturebeerlist.data.datasource.local.model.BeerLocalModel
import javax.inject.Inject

class BeersCacheDataSource @Inject constructor() {

    private val beersMutableList: MutableList<BeerLocalModel> = mutableListOf()
    var beers: List<BeerLocalModel>
        get() = beersMutableList
        set(value) {
            beersMutableList.clear()
            beersMutableList.addAll(value)
        }
}