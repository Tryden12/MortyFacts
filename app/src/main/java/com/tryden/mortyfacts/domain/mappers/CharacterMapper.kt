package com.tryden.mortyfacts.domain.mappers

import com.tryden.mortyfacts.network.response.GetCharacterByIdResponse
import com.tryden.mortyfacts.domain.models.Character

object CharacterMapper {

    fun buildFrom(response: GetCharacterByIdResponse) : Character {
        return Character(
            episodeList = emptyList(), // todo
            gender = response.gender,
            id = response.id,
            image = response.image,
            location =  Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = Character.Origin(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status
        )
    }
}