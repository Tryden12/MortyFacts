package com.tryden.mortyfacts.network

import com.tryden.mortyfacts.network.response.GetCharacterByIdResponse
import com.tryden.mortyfacts.network.response.GetCharactersPageResponse
import retrofit2.Response

class ApiClient(
    private val rickyAndMortyService: RickyAndMortyService
) {
    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return safeApiCall { rickyAndMortyService.getCharacterById(characterId) }
    }

    suspend fun getCharactersPage(pageIndex: Int) : SimpleResponse<GetCharactersPageResponse> {
        return safeApiCall { rickyAndMortyService.getCharactersPage(pageIndex) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}