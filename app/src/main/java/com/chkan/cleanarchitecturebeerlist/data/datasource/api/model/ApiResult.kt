package com.chkan.cleanarchitecturebeerlist.data.datasource.api.model

sealed class ApiResult{
    class Success (val data: List<BeerApi>) : ApiResult()
    class Error(val e: Exception) : ApiResult()
}
