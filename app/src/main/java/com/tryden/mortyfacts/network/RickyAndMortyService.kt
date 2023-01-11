package com.tryden.mortyfacts.network

import com.tryden.mortyfacts.network.response.GetCharacterByIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RickyAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId: Int
    ) : Response<GetCharacterByIdResponse>
}