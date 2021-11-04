package com.chkan.cleanarchitecturebeerlist.data.datasource.api.model

import kotlinx.serialization.Serializable

@Serializable
data class BeerApi(
    val id: Int? = null,
    val name: String? = null,
    val tagline: String? = null,
    val image: String? = null,
    val abv: Double? = null,
    val foodPairing: List<String>? = null
)
