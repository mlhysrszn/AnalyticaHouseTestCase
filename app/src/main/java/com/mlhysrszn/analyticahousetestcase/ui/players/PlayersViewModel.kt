package com.mlhysrszn.analyticahousetestcase.ui.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel

class PlayersViewModel : ViewModel() {
    private var _playersList = MutableLiveData<ArrayList<PlayerModel>>()
    val playersList: LiveData<ArrayList<PlayerModel>>
        get() = _playersList

    init {
        _playersList.value = getPlayersList()
    }

    private fun getPlayersList(): ArrayList<PlayerModel> {
        return arrayListOf(
            PlayerModel(
                1,
                "Ike",
                null,
                null,
                "Anigbogu",
                "C",
                TeamModel(12, "IND", "Indiana", "East", "Central", "Indiana Pacers", "Pacers"),
                null
            ),
            PlayerModel(
                2,
                "Alex",
                6,
                6,
                "Abrines",
                "G",
                TeamModel(
                    21,
                    "OKC",
                    "Oklahoma City",
                    "west",
                    "Northwest",
                    "Oklahoma City Thunder",
                    "Thunder"
                ),
                200
            ),
            PlayerModel(
                3,
                "Ike",
                null,
                null,
                "Anigbogu",
                "C",
                TeamModel(12, "IND", "Indiana", "East", "Central", "Indiana Pacers", "Pacers"),
                null
            ),
            PlayerModel(
                4,
                "Alex",
                6,
                6,
                "Abrines",
                "G",
                TeamModel(
                    21,
                    "OKC",
                    "Oklahoma City",
                    "west",
                    "Northwest",
                    "Oklahoma City Thunder",
                    "Thunder"
                ),
                200
            ),
            PlayerModel(
                5,
                "Ike",
                null,
                null,
                "Anigbogu",
                "C",
                TeamModel(12, "IND", "Indiana", "East", "Central", "Indiana Pacers", "Pacers"),
                null
            ),
            PlayerModel(
                6,
                "Alex",
                6,
                6,
                "Abrines",
                "G",
                TeamModel(
                    21,
                    "OKC",
                    "Oklahoma City",
                    "west",
                    "Northwest",
                    "Oklahoma City Thunder",
                    "Thunder"
                ),
                200
            ),
            PlayerModel(
                7,
                "Ike",
                null,
                null,
                "Anigbogu",
                "C",
                TeamModel(12, "IND", "Indiana", "East", "Central", "Indiana Pacers", "Pacers"),
                null
            ),
            PlayerModel(
                23,
                "Alex",
                6,
                8,
                "Abrines",
                "G",
                TeamModel(
                    21,
                    "OKC",
                    "Oklahoma City",
                    "west",
                    "Northwest",
                    "Oklahoma City Thunder",
                    "Thunder"
                ),
                200
            )
        )
    }
}