package com.mlhysrszn.analyticahousetestcase.ui.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel
import com.mlhysrszn.analyticahousetestcase.data.remote.ApiUtils
import com.mlhysrszn.analyticahousetestcase.data.remote.response.TeamsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsViewModel : ViewModel() {
    private var _teamsList = MutableLiveData<ArrayList<TeamModel>>()
    val teamsList: LiveData<ArrayList<TeamModel>>
        get() = _teamsList

    init {
        getTeamsList()
    }

    private fun getTeamsList() {
        val apiService = ApiUtils.getApiService()
        apiService.getTeams().enqueue(object : Callback<TeamsResponse> {
            override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                val teams = response.body()?.data
                if (teams != null) {
                    _teamsList.value = teams as ArrayList<TeamModel>
                }
            }

            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
            }
        })
    }
}