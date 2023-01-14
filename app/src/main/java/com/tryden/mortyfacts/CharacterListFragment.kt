package com.tryden.mortyfacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CharacterListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onResume() {
        super.onResume()

        view?.postDelayed({
            val navGraphActivity = activity as NavGraphActivity
            navGraphActivity.navController.navigate(R.id.action_characterListFragment_to_characterDetailFragment)
        }, 1_500)
    }
}