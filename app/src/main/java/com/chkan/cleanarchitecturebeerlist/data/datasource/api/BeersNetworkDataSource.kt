package com.chkan.cleanarchitecturebeerlist.data.datasource.api

import com.chkan.cleanarchitecturebeerlist.data.datasource.api.exceptions.handleNetworkExceptions
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.model.ApiResult
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.retrofit.ApiService
import java.lang.Exception
import javax.inject.Inject

const val MAX_RESULTS_PER_PAGE: Int = 20

class BeersNetworkDataSource @Inject constructor (private val beersApiService: ApiService) {

    val number = 5

  /*  suspend fun getAllBeers(page: String): Int {
        return try {
            val beers = beersApiService.getAllBeers(page, MAX_RESULTS_PER_PAGE.toString()).size
            beers
        } catch (ex: Exception) {
            0
            //ApiResult.Error(handleNetworkExceptions(ex))
        }
    }*/
}