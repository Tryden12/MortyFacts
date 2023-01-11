package com.tryden.mortyfacts.characters.detail

import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import com.tryden.mortyfacts.R
import com.tryden.mortyfacts.databinding.ModelCharacterDetailsDataPointBinding
import com.tryden.mortyfacts.databinding.ModelCharacterDetailsHeaderBinding
import com.tryden.mortyfacts.databinding.ModelCharacterDetailsImageBinding
import com.tryden.mortyfacts.epoxy.ViewBindingKotlinModel
import com.tryden.mortyfacts.network.response.GetCharacterByIdResponse

class CharacterDetailsEpoxyController: EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var characterResponse: GetCharacterByIdResponse? = null
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

        if (characterResponse == null) {
            // todo error state
            return
        }

        // Header Model
        HeaderEpoxyModel(
            name = characterResponse!!.name,
            gender = characterResponse!!.gender,
            status = characterResponse!!.status
        ).id("header").addTo(this)

        // Image Model
        ImageEpoxyModel(
            imageUrl = characterResponse!!.image
        ).id("image").addTo(this)

        // Data Point Models
        DataPointEpoxyModel(
            title = "Origin",
            description = characterResponse!!.origin.name
        ).id("origin").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = characterResponse!!.species
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
}