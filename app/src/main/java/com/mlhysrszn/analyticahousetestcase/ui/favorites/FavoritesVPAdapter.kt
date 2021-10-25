package com.mlhysrszn.analyticahousetestcase.ui.favorites

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mlhysrszn.analyticahousetestcase.ui.favplayers.FavPlayersFragment
import com.mlhysrszn.analyticahousetestcase.ui.favteams.FavTeamsFragment

class FavoritesVPAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavPlayersFragment()
            1 -> FavTeamsFragment()
            else -> FavPlayersFragment()
        }
    }
}