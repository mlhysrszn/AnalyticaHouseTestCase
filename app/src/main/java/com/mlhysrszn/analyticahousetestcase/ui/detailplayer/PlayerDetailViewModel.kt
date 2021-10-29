package com.mlhysrszn.analyticahousetestcase.ui.detailplayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mlhysrszn.analyticahousetestcase.data.local.FavoritesDatabase
import com.mlhysrszn.analyticahousetestcase.data.model.FavPlayerModel
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.data.remote.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerDetailViewModel(application: Application) : AndroidViewModel(application) {
    private var _player = MutableLiveData<PlayerModel>()
    val player: LiveData<PlayerModel>
        get() = _player

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val favDAO = FavoritesDatabase.getFavoritesDatabase(application)?.favoritesDAO()

    fun getPlayer(id: Int) {
        _isLoading.value = true
        val apiService = ApiUtils.getApiService()
        apiService.getPlayerDetail(id).enqueue(object : Callback<PlayerModel> {
            override fun onResponse(call: Call<PlayerModel>, response: Response<PlayerModel>) {
                val player = response.body()
                if (player != null) {
                    _player.value = player!!
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<PlayerModel>, t: Throwable) {
                println(t.localizedMessage?.toString())
                _isLoading.value = false
            }
        })
    }

    fun getPlayerId(playerId: Int): Int? {
        val player = favDAO?.getFavPlayer(playerId)
        return player?.playerId
    }

    fun insertOrDeleteFavPlayer(favPlayer: FavPlayerModel) {
        if (favDAO?.getFavPlayer(favPlayer.playerId) == null) {
            favDAO?.insertFavPlayer(favPlayer)
        } else {
            favDAO.deleteFavPlayer(favPlayer.playerId)
        }
    }
}