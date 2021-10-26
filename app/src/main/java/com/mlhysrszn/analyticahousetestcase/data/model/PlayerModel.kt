package com.mlhysrszn.analyticahousetestcase.data.model

import java.io.Serializable

data class PlayerModel(
    val id: Int,
    val firstName: String,
    val heightFeet: Int?,
    val heightInches: Int?,
    val lastName: String,
    val position: String,
    val team: TeamModel,
    val weightPounds: Int?
) : Serializable
