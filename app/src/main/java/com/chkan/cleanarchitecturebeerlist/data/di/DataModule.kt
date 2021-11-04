package com.chkan.cleanarchitecturebeerlist.data.di

import com.chkan.cleanarchitecturebeerlist.data.datasource.api.retrofit.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    private val URL_BASE = "https://api.punkapi.com/v2/"
    private val FILE_FAVORITES_BEERS = "FavoritesBeers.txt"

    @Provides
    fun provideRetrofit(): Retrofit {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}