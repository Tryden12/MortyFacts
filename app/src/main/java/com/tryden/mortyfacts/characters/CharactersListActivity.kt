package com.tryden.mortyfacts.characters

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.tryden.mortyfacts.MainActivity
import com.tryden.mortyfacts.R
import com.tryden.mortyfacts.util.Constants.INTENT_EXTRA_CHARACTER_ID

class CharactersListActivity: AppCompatActivity() {

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this)[CharactersViewModel::class.java]
    }

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        viewModel.charactersPagedListLiveData.observe(this) { pagedList ->
            epoxyController.submitList(pagedList)
        }

        findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)
    }

    private fun onCharacterSelected(characterId: Int) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(INTENT_EXTRA_CHARACTER_ID, characterId)
        startActivity(intent)
    }
}