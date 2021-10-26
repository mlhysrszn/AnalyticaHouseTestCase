package com.mlhysrszn.analyticahousetestcase.ui.detailteam

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel
import com.mlhysrszn.analyticahousetestcase.data.remote.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamDetailViewModel: ViewModel() {
    private var _team= MutableLiveData<TeamModel>()
    val team : LiveData<TeamModel>
        get() = _team

    fun getTeam(id: Int) {
        val apiService = ApiUtils.getApiService()
        apiService.getTeamDetail(id).enqueue(object: Callback<TeamModel> {
            override fun onResponse(call: Call<TeamModel>, response: Response<TeamModel>) {
                val team = response.body()
                if (team != null) {
                    _team.value = team!!
                }
            }

            override fun onFailure(call: Call<TeamModel>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }
        })
    }
}