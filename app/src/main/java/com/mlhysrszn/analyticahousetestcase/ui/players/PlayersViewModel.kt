package com.mlhysrszn.analyticahousetestcase.ui.players

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mlhysrszn.analyticahousetestcase.data.local.FavoritesDatabase
import com.mlhysrszn.analyticahousetestcase.data.model.FavPlayerModel
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.data.remote.ApiUtils
import com.mlhysrszn.analyticahousetestcase.data.remote.response.PlayersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayersViewModel(application: Application) : AndroidViewModel(application) {
    private var _playersList = MutableLiveData<ArrayList<PlayerModel>>()
    val playersList: LiveData<ArrayList<PlayerModel>>
        get() = _playersList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val favDAO = FavoritesDatabase.getFavoritesDatabase(application)?.favoritesDAO()

    init {
        getPlayersList()
    }

    private fun getPlayersList() {
        _isLoading.value = true
        val apiService = ApiUtils.getApiService()
        apiService.getPlayers().enqueue(object : Callback<PlayersResponse> {
            override fun onResponse(
                call: Call<PlayersResponse>,
                response: Response<PlayersResponse>
            ) {
                val players = response.body()?.data
                if (players != null) {
                    _playersList.value = players as ArrayList<PlayerModel>
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<PlayersResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
                _isLoading.value = false
            }
        })
    }

    fun insertOrDeleteFavPlayer(favPlayer: FavPlayerModel) {
        if (favDAO?.getFavPlayer(favPlayer.playerId) == null) {
            favDAO?.insertFavPlayer(favPlayer)
        } else {
            favDAO.deleteFavPlayer(favPlayer.playerId)
        }
    }

    fun getPlayer(playerId: Int): Int? {
        val player = favDAO?.getFavPlayer(playerId)
        return player?.playerId
    }

    fun favPlayerModel(model: PlayerModel): FavPlayerModel {
        return FavPlayerModel(
            model.id,
            model.firstName,
            model.heightFeet,
            model.heightInches,
            model.lastName,
            model.position,
            FavTeamModel(
                model.team.id,
                model.team.abbreviation,
                model.team.city,
                model.team.conference,
                model.team.division,
                model.team.fullName,
                model.team.name
            ),
            model.weightPounds
        )
    }
}