package com.chkan.cleanarchitecturebeerlist.data.repository.mapper

import com.chkan.cleanarchitecturebeerlist.core.BaseMapper
import com.chkan.cleanarchitecturebeerlist.data.datasource.api.model.BeerApi
import com.chkan.cleanarchitecturebeerlist.data.datasource.local.model.BeerLocalModel
import com.chkan.cleanarchitecturebeerlist.features.beers.domain.model.BeerEntity
import com.chkan.cleanarchitecturebeerlist.features.beers.domain.model.BeersEntity


object ApiToEntityMapper : BaseMapper<List<BeerApi>, BeersEntity> {
    override fun map(type: List<BeerApi>?): BeersEntity {
        return BeersEntity(
                type?.map {
                    BeerEntity(
                            id = it.id ?: -1,
                            name = it.name ?: "",
                            tagline = it.tagline ?: "",
                            image = it.image ?: "",
                            abv = it.abv ?: -1.0,
                            isFavorite = false,
                            foodPairing = it.foodPairing ?: emptyList()
                    )
                } ?: listOf()
        )
    }
}

object EntityToLocalMapper : BaseMapper<BeerEntity, BeerLocalModel> {
    override fun map(type: BeerEntity?): BeerLocalModel {
        return BeerLocalModel(
                id = type!!.id,
                name = type.name,
                tagline = type.tagline,
                image = type.image,
                abv = type.abv,
                isFavorite = true,
                foodPairing = type.foodPairing
        )
    }
}

object LocalToEntityMapper : BaseMapper<List<BeerLocalModel>, BeersEntity> {
    override fun map(type: List<BeerLocalModel>?): BeersEntity {
        return BeersEntity(
                beers = type?.map {
                    BeerEntity(
                            id = it.id,
                            name = it.name,
                            tagline = it.tagline,
                            image = it.image,
                            abv = it.abv,
                            isFavorite = it.isFavorite,
                            foodPairing = it.foodPairing
                    )
                } ?: listOf()
        )
    }
}

object ApiToLocalModelMapper : BaseMapper<List<BeerApi>, List<BeerLocalModel>> {
    override fun map(type: List<BeerApi>?): List<BeerLocalModel> {
        return type?.map {
            BeerLocalModel(
                    id = it.id ?: -1,
                    name = it.name ?: "",
                    tagline = it.tagline ?: "",
                    image = it.image ?: "",
                    abv = it.abv ?: 0.0,
                    isFavorite = false,
                    foodPairing = it.foodPairing ?: emptyList()
            )
        } ?: emptyList()
    }
}
