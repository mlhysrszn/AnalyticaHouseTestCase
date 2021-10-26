package com.mlhysrszn.analyticahousetestcase.data.remote.response

import com.google.gson.annotations.SerializedName
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel

data class PlayersResponse(

    @SerializedName("data")
    val data: List<PlayerModel>
)