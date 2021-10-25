package com.mlhysrszn.analyticahousetestcase.ui.favplayers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel

class FavPlayersViewModel : ViewModel() {
    private var _favPlayersList = MutableLiveData<ArrayList<PlayerModel>>()
    val favPlayersList: LiveData<ArrayList<PlayerModel>>
        get() = _favPlayersList

    init {
        _favPlayersList.value = getFavPlayersList()
    }

    private fun getFavPlayersList(): ArrayList<PlayerModel> {
        return arrayListOf(
            PlayerModel(
                1,
                "IkeAAAA",
                89,
                32,
                "Anigbogu",
                "C",
                TeamModel(12, "IND", "Indiana", "East", "Central", "Indiana Pacers", "Pacers"),
                444
            ),
            PlayerModel(
                2,
                "BBB",
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
                "IkeSSSSS",
                null,
                9,
                "Anigbogu",
                "C",
                TeamModel(12, "IND", "Indiana", "East", "Central", "Indiana Pacers", "Pacers"),
                100
            ),
            PlayerModel(
                4,
                "AlWQRWQWRQex",
                6,
                6,
                "AbrWQEines",
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
                7,
                null,
                "Anigbogu",
                "C",
                TeamModel(12, "IND", "Indiana", "East", "Central", "Indiana Pacers", "Pacers"),
                null
            ),
            PlayerModel(
                6,
                "XXX",
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
            )
        )
    }
}