package com.chkan.cleanarchitecturebeerlist.features.beers.vm.model

class BeerUI(
    val id: Int,
    val name: String,
    val tagline: String,
    val image: String,
    val abv: Double,
    val abvColorType: AbvColorType,
    var isFavorite: Boolean = false,
    val foodPairing: List<String>
)