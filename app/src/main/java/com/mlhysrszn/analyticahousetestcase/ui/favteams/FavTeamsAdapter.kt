package com.mlhysrszn.analyticahousetestcase.ui.favteams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemTeamBinding

class FavTeamsAdapter(private val favTeamsList: ArrayList<TeamModel>) :
    RecyclerView.Adapter<FavTeamsAdapter.FavTeamsViewHolder>() {

    class FavTeamsViewHolder(private val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamModel) {
            binding.apply {
                team = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamsViewHolder {
        val itemTeamBinding =
            ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavTeamsViewHolder(itemTeamBinding)
    }

    override fun onBindViewHolder(holder: FavTeamsViewHolder, position: Int) {
        val favTeam = favTeamsList[position]
        holder.bind(favTeam)
    }

    override fun getItemCount(): Int = favTeamsList.size
}