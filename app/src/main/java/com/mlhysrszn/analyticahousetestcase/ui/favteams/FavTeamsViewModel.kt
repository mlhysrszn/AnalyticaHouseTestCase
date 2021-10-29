package com.mlhysrszn.analyticahousetestcase.ui.favteams

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mlhysrszn.analyticahousetestcase.data.local.FavoritesDatabase
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel

class FavTeamsViewModel(application: Application) : AndroidViewModel(application) {
    private val _favTeamsList: MutableLiveData<List<FavTeamModel>?> =
        MutableLiveData<List<FavTeamModel>?>()
    val favTeamsList: LiveData<List<FavTeamModel>?>
        get() = _favTeamsList

    private val favDAO = FavoritesDatabase.getFavoritesDatabase(application)?.favoritesDAO()

    init {
        _favTeamsList.value = getFavTeamsList()
    }

    private fun getFavTeamsList(): List<FavTeamModel>? {
        return favDAO?.getAllFavTeams()
    }

    fun deleteFavTeam(favTeamId: Int) {
        favDAO?.deleteFavTeam(favTeamId)
        _favTeamsList.value = getFavTeamsList()
    }
}