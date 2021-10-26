package com.mlhysrszn.analyticahousetestcase.ui.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemTeamBinding

class TeamsAdapter(private val teamsList: ArrayList<TeamModel>) :
    RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    class TeamsViewHolder(private val binding: ItemTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamModel) {
            binding.apply {
                team = item
                root.setOnClickListener {
                    val action =
                        TeamsFragmentDirections.actionTeamsFragmentToTeamDetailFragment(item.id)
                    it.findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val itemTeamBinding =
            ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamsViewHolder(itemTeamBinding)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val team = teamsList[position]
        holder.bind(team)
    }

    override fun getItemCount(): Int = teamsList.size
}