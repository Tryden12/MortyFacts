package com.tryden.mortyfacts.characters.detail

import android.annotation.SuppressLint
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.R
import com.tryden.mortyfacts.databinding.ModelCharacterDetailsDataPointBinding
import com.tryden.mortyfacts.databinding.ModelCharacterDetailsHeaderBinding
import com.tryden.mortyfacts.databinding.ModelCharacterDetailsImageBinding
import com.tryden.mortyfacts.databinding.ModelEpisodeCarouselItemBinding
import com.tryden.mortyfacts.databinding.ModelTitleBinding
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import  com.tryden.mortyfacts.domain.models.Character
import com.tryden.mortyfacts.domain.models.Episode

class CharacterDetailsEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var character: Character? = null
        set(value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if(isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if (character == null) {
            // todo error state
            return
        }

        // Header Model
        HeaderEpoxyModel(
            name = character!!.name,
            gender = character!!.gender,
            status = character!!.status
        ).id("header").addTo(this)

        // Image Model
        ImageEpoxyModel(
            imageUrl = character!!.image
        ).id("image").addTo(this)

        // Episode carousel list section
        if (character!!.episodeList.isNotEmpty()) {
            val items = character!!.episodeList.map {
                EpisodeCarouselItemEpoxyModel(it).id(it.id)
            }

            TitleEpoxyModel(title ="Episodes").id("title_episodes").addTo(this)
            CarouselModel_()
                .id("episode_carousel")
                .models(items)
                .numViewsToShowOnScreen(1.15f)
                .addTo(this)
        }

        // Data Point Models
        DataPointEpoxyModel(
            title = "Origin",
            description = character!!.origin.name
        ).id("origin").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = character!!.species
        ).id("species").addTo(this)
    }

    data class HeaderEpoxyModel(
        val name: String,
        val gender: String,
        val status: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header) {

        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status

            if (gender.equals("male", true)) {
                genderImageView.setImageResource(R.drawable.ic_male_24)
            } else {
                genderImageView.setImageResource(R.drawable.ic_female_24)
            }
        }
    }

    data class ImageEpoxyModel(
        val imageUrl: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image) {

        override fun ModelCharacterDetailsImageBinding.bind() {
            Picasso.get().load(imageUrl).into(headerImageView)
        }
    }

    data class DataPointEpoxyModel(
        val title: String,
        val description: String
    ) : ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {

        override fun ModelCharacterDetailsDataPointBinding.bind() {
            labelTextView.text = title
            textView.text = description
        }
    }

    data class EpisodeCarouselItemEpoxyModel(
        val episode: Episode
    ) : ViewBindingKotlinModel<ModelEpisodeCarouselItemBinding>(R.layout.model_episode_carousel_item) {

        override fun ModelEpisodeCarouselItemBinding.bind() {
            episodeTextView.text = episode.episode
            episodeDetailsTextView.text = "${episode.name}\n${episode.airDate}"
        }
    }

    data class TitleEpoxyModel(
        val title: String
    ) : ViewBindingKotlinModel<ModelTitleBinding>(R.layout.model_title) {

        override fun ModelTitleBinding.bind() {
            titleTextView.text = title
        }
    }
}