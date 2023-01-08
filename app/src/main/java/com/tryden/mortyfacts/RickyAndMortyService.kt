package com.tryden.mortyfacts

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RickyAndMortyService {

    @GET("character/{character-id}")
    fun getCharacterById(
        @Path("character-id") characterId: Int
    ) : Call<GetCharacterByIdResponse>
}