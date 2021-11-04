package com.chkan.cleanarchitecturebeerlist.data.datasource.api

import com.chkan.cleanarchitecturebeerlist.data.datasource.api.exceptions.handleNetworkExceptions
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.model.ApiResult
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.retrofit.ApiService
import java.lang.Exception

const val MAX_RESULTS_PER_PAGE: Int = 20

class BeersNetworkDataSource(private val beersApiService: ApiService) {

    suspend fun getAllBeers(page: String): ApiResult {
        return try {
            val beers = beersApiService.getAllBeers(page, MAX_RESULTS_PER_PAGE.toString())
            ApiResult.Success(beers)
        } catch (ex: Exception) {
            ApiResult.Error(handleNetworkExceptions(ex))
        }
    }
}