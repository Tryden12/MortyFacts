package com.tryden.mortyfacts

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tryden.mortyfacts.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkLayer {
    // Moshi
    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    // Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    // Service
    val rickyAndMortyService: RickyAndMortyService by lazy {
        retrofit.create(RickyAndMortyService::class.java)
    }

    // Client
    val apiClient = ApiClient(rickyAndMortyService)
}