package com.chkan.cleanarchitecturebeerlist.data.datasource.api

import com.chkan.cleanarchitecturebeerlist.data.datasource.api.exceptions.handleNetworkExceptions
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.model.BeerApi
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.retrofit.ApiService
import com.chkan.cleanarchitecturebeerlist.core.datatype.Result
import java.lang.Exception
import javax.inject.Inject

const val MAX_RESULTS_PER_PAGE: Int = 20

class BeersNetworkDataSource @Inject constructor (private val beersApiService: ApiService) {

    suspend fun getAllBeers(page: String): Result<List<BeerApi>> {
        return try {
            val beers = beersApiService.getAllBeers(page, MAX_RESULTS_PER_PAGE.toString())
            Result.success(beers)
        } catch (ex: Exception) {
            Result.error(handleNetworkExceptions(ex))
        }
    }
}