package com.mlhysrszn.analyticahousetestcase.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_player")
data class FavPlayerModel(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "player_id")
    val playerId: Int,

    @ColumnInfo(name = "first_name")
    val firstName: String,

    @ColumnInfo(name = "height_feet")
    val heightFeet: Int?,

    @ColumnInfo(name = "height_inches")
    val heightInches: Int?,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "position")
    val position: String,

    @Embedded
    val team: FavTeamModel,

    @ColumnInfo(name = "weight_pounds")
    val weightPounds: Int?
)
