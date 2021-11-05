package com.chkan.cleanarchitecturebeerlist.features.beers.domain.usecase

import com.chkan.cleanarchitecturebeerlist.core.datatype.ResultType
import com.chkan.cleanarchitecturebeerlist.core.datatype.Result
import com.chkan.cleanarchitecturebeerlist.data.repository.BeersRepositoryImpl
import com.chkan.cleanarchitecturebeerlist.features.beers.domain.model.BeersEntity
import javax.inject.Inject

class GetBeersUseCase @Inject constructor (private val beersRepository: BeersRepositoryImpl) {

    suspend fun execute(): Result<BeersEntity> {
        var beers: Result<BeersEntity> = Result.success(BeersEntity(listOf()))

        beersRepository.getAllBeers()?.let { beersEntity ->
            val resultType = beersEntity.resultType

            if (resultType == ResultType.SUCCESS) {
                beersEntity.data?.let {

                    val sortedBeers =
                            getSortedAscendingBeers(BeersEntity(it.beers.toMutableList()))

                    beers = Result.success(sortedBeers)
                }
            } else {
                beers = Result.error(beersEntity.error)
            }
        }

        return beers
    }

    private fun getSortedAscendingBeers(beersEntity: BeersEntity): BeersEntity {
        return BeersEntity(beersEntity.beers.sortedBy { it.abv })
    }
}
