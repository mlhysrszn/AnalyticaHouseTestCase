package com.mlhysrszn.analyticahousetestcase.data.remote

import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel
import com.mlhysrszn.analyticahousetestcase.data.remote.response.PlayersResponse
import com.mlhysrszn.analyticahousetestcase.data.remote.response.TeamsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("players")
    fun getPlayers(): Call<PlayersResponse>

    @GET("teams")
    fun getTeams(): Call<TeamsResponse>

    @GET("players/{id}")
    fun getPlayerDetail(
        @Path("id") id: Int
    ): Call<PlayerModel>

    @GET("teams/{id}")
    fun getTeamDetail(
        @Path("id") id: Int
    ): Call<TeamModel>
}