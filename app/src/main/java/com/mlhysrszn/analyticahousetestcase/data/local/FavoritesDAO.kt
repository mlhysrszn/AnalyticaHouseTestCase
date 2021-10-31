package com.mlhysrszn.analyticahousetestcase.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mlhysrszn.analyticahousetestcase.data.model.FavPlayerModel
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel

@Dao
interface FavoritesDAO {

    @Insert
    fun insertFavPlayer(favPlayer: FavPlayerModel)

    @Insert
    fun insertFavTeam(favTeam: FavTeamModel)

    @Query("SELECT * FROM fav_player")
    fun getAllFavPlayers(): List<FavPlayerModel>

    @Query("SELECT * FROM fav_team")
    fun getAllFavTeams(): List<FavTeamModel>

    @Query("SELECT * FROM fav_player WHERE player_id = :favPlayerId")
    fun getFavPlayer(favPlayerId: Int): FavPlayerModel?

    @Query("DELETE FROM fav_player WHERE player_id = :favPlayerId")
    fun deleteFavPlayer(favPlayerId: Int)

    @Query("SELECT * FROM fav_team WHERE team_id = :favTeamId")
    fun getFavTeam(favTeamId: Int): FavTeamModel?

    @Query("DELETE FROM fav_team WHERE team_id = :favTeamId")
    fun deleteFavTeam(favTeamId: Int)
}