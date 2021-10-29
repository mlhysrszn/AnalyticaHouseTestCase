package com.mlhysrszn.analyticahousetestcase.ui.favplayers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mlhysrszn.analyticahousetestcase.data.local.FavoritesDatabase
import com.mlhysrszn.analyticahousetestcase.data.model.FavPlayerModel

class FavPlayersViewModel(application: Application) : AndroidViewModel(application) {
    private var _favPlayersList: MutableLiveData<List<FavPlayerModel>?> =
        MutableLiveData<List<FavPlayerModel>?>()
    val favPlayersList: LiveData<List<FavPlayerModel>?>
        get() = _favPlayersList

    private val favDAO = FavoritesDatabase.getFavoritesDatabase(application)?.favoritesDAO()

    init {
        _favPlayersList.value = getFavPlayersList()
    }

    private fun getFavPlayersList(): List<FavPlayerModel>? {
        return favDAO?.getAllFavPlayers()
    }

    fun deleteFavPlayer(favPlayerId: Int) {
        favDAO?.deleteFavPlayer(favPlayerId)
        _favPlayersList.value = getFavPlayersList()
    }
}