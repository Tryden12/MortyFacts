package com.tryden.mortyfacts.characters

import com.tryden.mortyfacts.network.NetworkLayer
import com.tryden.mortyfacts.network.response.GetCharacterByIdResponse
import com.tryden.mortyfacts.network.response.GetCharactersPageResponse

class CharactersRepository {

    suspend fun getCharactersPage(pageIndex: Int): GetCharactersPageResponse? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }
}