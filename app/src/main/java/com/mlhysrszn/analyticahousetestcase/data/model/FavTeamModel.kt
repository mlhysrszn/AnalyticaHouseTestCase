package com.mlhysrszn.analyticahousetestcase.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_team")
data class FavTeamModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "team_id")
    val teamId: Int,

    @ColumnInfo(name = "abbreviation")
    val abbreviation: String,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "conference")
    val conference: String,

    @ColumnInfo(name = "division")
    val division: String,

    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "name")
    val name: String
)
