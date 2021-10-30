package com.mlhysrszn.analyticahousetestcase.ui.teams

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mlhysrszn.analyticahousetestcase.data.local.FavoritesDatabase
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel
import com.mlhysrszn.analyticahousetestcase.data.remote.ApiUtils
import com.mlhysrszn.analyticahousetestcase.data.remote.response.TeamsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsViewModel(application: Application) : AndroidViewModel(application) {
    private var _teamsList = MutableLiveData<ArrayList<TeamModel>>()
    val teamsList: LiveData<ArrayList<TeamModel>>
        get() = _teamsList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val favDAO = FavoritesDatabase.getFavoritesDatabase(application)?.favoritesDAO()

    init {
        getTeamsList()
    }

    private fun getTeamsList() {
        _isLoading.value = true
        val apiService = ApiUtils.getApiService()
        apiService.getTeams().enqueue(object : Callback<TeamsResponse> {
            override fun onResponse(call: Call<TeamsResponse>, response: Response<TeamsResponse>) {
                val teams = response.body()?.data
                if (teams != null) {
                    _teamsList.value = teams as ArrayList<TeamModel>
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                println(t.localizedMessage?.toString())
                _isLoading.value = false
            }
        })
    }

    fun insertOrDeleteFavTeam(favTeam: FavTeamModel) {
        if (favDAO?.getFavTeam(favTeam.teamId) == null) {
            favDAO?.insertFavTeam(favTeam)
        }
        else {
            favDAO.deleteFavTeam(favTeam.teamId)
        }
    }

    fun getTeam(teamId: Int): Int? {
        val team = favDAO?.getFavTeam(teamId)
        return team?.teamId
    }

    fun favTeamModel(model: TeamModel): FavTeamModel {
        return FavTeamModel(
            model.id,
            model.abbreviation,
            model.city,
            model.conference,
            model.division,
            model.fullName,
            model.name
        )
    }
}