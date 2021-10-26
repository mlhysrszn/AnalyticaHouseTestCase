package com.mlhysrszn.analyticahousetestcase.data.remote.response

import com.google.gson.annotations.SerializedName
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel

data class TeamsResponse(

    @SerializedName("data")
    val data: List<TeamModel>
)