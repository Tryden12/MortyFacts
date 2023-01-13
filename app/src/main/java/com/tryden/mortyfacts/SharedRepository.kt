package com.tryden.mortyfacts

import com.tryden.mortyfacts.domain.mappers.CharacterMapper
import com.tryden.mortyfacts.network.NetworkLayer
import com.tryden.mortyfacts.domain.models.Character

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed) {
            return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return CharacterMapper.buildFrom(response = request.body)
    }
}
