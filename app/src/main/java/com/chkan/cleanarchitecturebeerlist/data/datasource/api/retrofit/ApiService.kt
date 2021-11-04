package com.chkan.cleanarchitecturebeerlist.data.datasource.api.retrofit

import com.chkan.cleanarchitecturebeerlist.data.datasource.api.model.BeerApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val URL =
    "https://mommys.com.ua/"

private val contentType =  "application/json".toMediaType()
private val jsonConfig = JsonConfiguration.Stable.copy(prettyPrint = true, ignoreUnknownKeys = true)
private val json = Json(jsonConfig)

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(URL)
    .addConverterFactory(json.asConverterFactory(contentType))
    .build()

interface ApiService {

    @GET("beers?")
    suspend fun getAllBeers(
        @Query("page") page: String,
        @Query("per_page") perPage: String
    ): List<BeerApi>
}

object Api {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}