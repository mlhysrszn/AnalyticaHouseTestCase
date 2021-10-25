package com.mlhysrszn.analyticahousetestcase.ui.favteams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel

class FavTeamsViewModel : ViewModel() {
    private val _favTeamsList = MutableLiveData<ArrayList<TeamModel>>()
    val favTeamsList: LiveData<ArrayList<TeamModel>>
        get() = _favTeamsList

    init {
        _favTeamsList.value = getFavTeamsList()
    }

    private fun getFavTeamsList(): ArrayList<TeamModel> {
        return arrayListOf(
            TeamModel(1, "ATL", "Atlanta", "East", "Southeast", "Atlanta Hawks", "Hawks"),
            TeamModel(2, "BOS", "Boston", "East", "Atlantic", "Boston Celtics", "Celtics"),
            TeamModel(3, "NYK", "New York", "East", "Atlantic", "New York Knicks", "Knicks"),
            TeamModel(4, "OKC", "Oklahoma City", "West", "Northwest", "Oklahoma City Thunder", "Thunder"),
            TeamModel(5, "ORL", "Orlando", "Southeast", "Southeast", "Orlando Magic", "Magic"),
            TeamModel(6, "SAS", "San Antonio", "West", "Southwest", "San Antonio Spurs", "Spurs"),
            )
    }
}