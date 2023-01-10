package com.tryden.mortyfacts

import retrofit2.Response

class ApiClient(
    private val rickyAndMortyService: RickyAndMortyService
) {
    suspend fun getCharacterById(characterId: Int): Response<GetCharacterByIdResponse> {
        return rickyAndMortyService.getCharacterById(characterId)
    }
}