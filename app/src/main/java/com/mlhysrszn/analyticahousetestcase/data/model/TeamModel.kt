package com.mlhysrszn.analyticahousetestcase.data.model

import java.io.Serializable

data class TeamModel(
    val id: Int,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
    val fullName: String,
    val name: String
) : Serializable
