package com.tryden.mortyfacts

import com.tryden.mortyfacts.domain.mappers.CharacterMapper
import com.tryden.mortyfacts.network.NetworkLayer
import com.tryden.mortyfacts.domain.models.Character
import com.tryden.mortyfacts.network.response.GetCharacterByIdResponse
import com.tryden.mortyfacts.network.response.GetEpisodeByIdResponse

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)
        return CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisodes
        )
    }

    private suspend fun getEpisodesFromCharacterResponse(
        characterResponse: GetCharacterByIdResponse
    ) : List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }

        return request.body
    }
}
