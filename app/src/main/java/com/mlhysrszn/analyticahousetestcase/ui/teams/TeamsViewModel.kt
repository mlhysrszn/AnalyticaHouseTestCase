package com.mlhysrszn.analyticahousetestcase.ui.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel

class TeamsViewModel : ViewModel() {
    private var _teamsList = MutableLiveData<ArrayList<TeamModel>>()
    val teamsList: LiveData<ArrayList<TeamModel>>
        get() = _teamsList

    init {
        _teamsList.value = getTeamsList()
    }

    fun getTeamsList(): ArrayList<TeamModel> {
        return arrayListOf(
            TeamModel(1, "ATL", "Atlanta", "East", "Southeast", "Atlanta Hawks", "Hawks"),
            TeamModel(2, "BOS", "Boston", "East", "Atlantic", "Boston Celtics", "Celtics"),
            TeamModel(3, "ATL", "Atlanta", "East", "Southeast", "Atlanta Hawks", "Hawks"),
            TeamModel(4, "BOS", "Boston", "East", "Atlantic", "Boston Celtics", "Celtics"),
            TeamModel(5, "ATL", "Atlanta", "East", "Southeast", "Atlanta Hawks", "Hawks"),
            TeamModel(6, "BOS", "Boston", "East", "Atlantic", "Boston Celtics", "Celtics"),
            TeamModel(7, "ATL", "Atlanta", "East", "Southeast", "Atlanta Hawks", "Hawks"),
            TeamModel(8, "BOS", "Boston", "East", "Atlantic", "Boston Celtics", "Celtics")
        )
    }
}