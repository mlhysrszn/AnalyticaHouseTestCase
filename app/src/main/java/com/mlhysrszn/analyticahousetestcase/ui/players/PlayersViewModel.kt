package com.mlhysrszn.analyticahousetestcase.ui.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.data.remote.ApiUtils
import com.mlhysrszn.analyticahousetestcase.data.remote.response.PlayersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayersViewModel : ViewModel() {
    private var _playersList = MutableLiveData<ArrayList<PlayerModel>>()
    val playersList: LiveData<ArrayList<PlayerModel>>
        get() = _playersList

    init {
        getPlayersList()
    }

    private fun getPlayersList() {
        val apiService = ApiUtils.getApiService()
        apiService.getPlayers().enqueue(object : Callback<PlayersResponse> {
            override fun onResponse(
                call: Call<PlayersResponse>,
                response: Response<PlayersResponse>
            ) {
                val players = response.body()?.data
                if (players != null) {
                    _playersList.value = players as ArrayList<PlayerModel>
                }
            }

            override fun onFailure(call: Call<PlayersResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }
        })
    }
}