package com.tryden.mortyfacts

import retrofit2.Call
import retrofit2.http.GET

interface RickyAndMortyService {

    @GET("character/2")
    fun getCharacterById() : Call<GetCharacterByIdResponse>
}