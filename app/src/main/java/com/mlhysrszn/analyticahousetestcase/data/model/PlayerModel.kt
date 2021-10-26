package com.mlhysrszn.analyticahousetestcase.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PlayerModel(
    @SerializedName("id")
    val id: Int,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("height_feet")
    val heightFeet: Int?,

    @SerializedName("height_inches")
    val heightInches: Int?,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("position")
    val position: String,

    @SerializedName("team")
    val team: TeamModel,

    @SerializedName("weight_pounds")
    val weightPounds: Int?
)
