package com.chkan.cleanarchitecturebeerlist.data.datasource.api.retrofit

import com.chkan.cleanarchitecturebeerlist.data.datasource.api.model.BeerApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("beers?")
    suspend fun getAllBeers(
        @Query("page") page: String,
        @Query("per_page") perPage: String
    ): List<BeerApi>
}