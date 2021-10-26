package com.mlhysrszn.analyticahousetestcase.ui.detailplayer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.data.remote.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerDetailViewModel : ViewModel() {
    private var _player = MutableLiveData<PlayerModel>()
    val player: LiveData<PlayerModel>
        get() = _player

    fun getPlayer(id: Int) {
        val apiService = ApiUtils.getApiService()
        apiService.getPlayerDetail(id).enqueue(object : Callback<PlayerModel> {
            override fun onResponse(call: Call<PlayerModel>, response: Response<PlayerModel>) {
                val player = response.body()
                if (player != null) {
                    _player.value = player!!
                }
            }

            override fun onFailure(call: Call<PlayerModel>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }
        })
    }
}