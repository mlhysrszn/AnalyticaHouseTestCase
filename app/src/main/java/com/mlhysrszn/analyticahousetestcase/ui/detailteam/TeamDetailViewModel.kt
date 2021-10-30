package com.mlhysrszn.analyticahousetestcase.ui.detailteam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mlhysrszn.analyticahousetestcase.data.local.FavoritesDatabase
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel
import com.mlhysrszn.analyticahousetestcase.data.remote.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamDetailViewModel(application: Application): AndroidViewModel(application) {
    private var _team= MutableLiveData<TeamModel>()
    val team : LiveData<TeamModel>
        get() = _team

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val favDAO = FavoritesDatabase.getFavoritesDatabase(application)?.favoritesDAO()

    fun getTeam(id: Int) {
        _isLoading.value = true
        val apiService = ApiUtils.getApiService()
        apiService.getTeamDetail(id).enqueue(object: Callback<TeamModel> {
            override fun onResponse(call: Call<TeamModel>, response: Response<TeamModel>) {
                val team = response.body()
                if (team != null) {
                    _team.value = team!!
                    _isLoading.value = false
                }
            }

            override fun onFailure(call: Call<TeamModel>, t: Throwable) {
                println(t.localizedMessage?.toString())
                _isLoading.value = false
            }
        })
    }

    fun getTeamId(teamId: Int): Int? {
        val team = favDAO?.getFavTeam(teamId)
        return team?.teamId
    }

    fun insertOrDeleteFavTeam(favTeam: FavTeamModel) {
        if (favDAO?.getFavTeam(favTeam.teamId) == null) {
            favDAO?.insertFavTeam(favTeam)
        } else {
            favDAO.deleteFavTeam(favTeam.teamId)
        }
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