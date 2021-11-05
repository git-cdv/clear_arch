package com.chkan.cleanarchitecturebeerlist.data.repository

import com.chkan.cleanarchitecturebeerlist.core.datatype.ResultType
import com.chkan.cleanarchitecturebeerlist.core.datatype.Result
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.BeersNetworkDataSource
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.MAX_RESULTS_PER_PAGE
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.exceptions.BadRequestException
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.model.BeerApi
import com.chkan.cleanarchitecturebeerlist.data.datasource.cache.BeersCacheDataSource
import com.chkan.cleanarchitecturebeerlist.data.datasource.local.model.BeerLocalModel
import com.chkan.cleanarchitecturebeerlist.features.beers.domain.BeersRepository
import com.chkan.cleanarchitecturebeerlist.features.beers.domain.model.BeersEntity
import com.chkan.cleanarchitecturebeerlist.data.repository.mapper.ApiToEntityMapper
import com.chkan.cleanarchitecturebeerlist.data.repository.mapper.ApiToLocalModelMapper
import com.chkan.cleanarchitecturebeerlist.data.repository.mapper.LocalToEntityMapper
import javax.inject.Inject

class BeersRepositoryImpl @Inject constructor (
    private val beersNetworkDataSource: BeersNetworkDataSource,
    private val beersCacheDataSource: BeersCacheDataSource
) : BeersRepository {

    override suspend fun getAllBeers(): Result<BeersEntity>? {
        var page = -1
        var result: Result<BeersEntity>?
        val mutableBeers: MutableList<BeerApi> = mutableListOf()

        val allBeers: List<BeerLocalModel> = beersCacheDataSource.beers
        if (allBeers.isNotEmpty()) return Result.success(LocalToEntityMapper.map(allBeers))

        do {
            page = getPageToCheckBeers(page, mutableBeers.isNotEmpty(), mutableBeers.size)

            beersNetworkDataSource.getAllBeers(page.toString()).let { resultListBeerResponse ->
                if (resultListBeerResponse.resultType == ResultType.SUCCESS) {
                    resultListBeerResponse.data?.let {
                        mutableBeers.addAll(resultListBeerResponse.data)
                    }
                }

                result = if (resultListBeerResponse.resultType == ResultType.SUCCESS ||
                        (resultListBeerResponse.error is BadRequestException && mutableBeers.isNotEmpty())) {
                    Result.success(ApiToEntityMapper.map(mutableBeers.toList()))
                } else {
                    Result.error(resultListBeerResponse.error)
                }
            }
        } while (result?.resultType != Result.error<Error>().resultType && page != -1)

        if (result?.resultType == ResultType.SUCCESS) beersCacheDataSource.beers = ApiToLocalModelMapper.map(mutableBeers.toList())

        return result
    }

    private fun getPageToCheckBeers(currentPage: Int, isMutableBeersNotEmpty: Boolean, beersSize: Int): Int {
        var page: Int = currentPage

        if (isMutableBeersNotEmpty) {
            if (isNecessaryFetchMoreBeers(currentPage, beersSize)) page++ else page = -1
        } else {
            page = 1
        }

        return page
    }

    private fun isNecessaryFetchMoreBeers(page: Int, beersSize: Int): Boolean {
        return (beersSize / page) == MAX_RESULTS_PER_PAGE
    }

}
