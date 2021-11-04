package com.chkan.cleanarchitecturebeerlist.data.datasource.api.model

data class BeerApi(
    val id: Int? = null,
    val name: String? = null,
    val tagline: String? = null,
    val image: String? = null,
    val abv: Double? = null,
    val foodPairing: List<String>? = null
)
