package com.tryden.mortyfacts.domain.mappers

import com.tryden.mortyfacts.domain.models.Episode
import com.tryden.mortyfacts.network.response.GetEpisodeByIdResponse

object EpisodeMapper {

    fun buildFrom(networkEpisode: GetEpisodeByIdResponse) : Episode {
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.air_date,
            episode = networkEpisode.episode
        )
    }
}