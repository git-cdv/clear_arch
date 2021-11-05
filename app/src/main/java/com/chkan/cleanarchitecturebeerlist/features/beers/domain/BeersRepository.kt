package com.chkan.cleanarchitecturebeerlist.features.beers.domain

import com.chkan.cleanarchitecturebeerlist.features.beers.domain.model.BeersEntity
import com.chkan.cleanarchitecturebeerlist.core.datatype.Result

interface BeersRepository {
    suspend fun getAllBeers(): Result<BeersEntity>?
}