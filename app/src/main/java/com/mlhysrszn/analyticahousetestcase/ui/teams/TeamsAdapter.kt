package com.mlhysrszn.analyticahousetestcase.ui.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mlhysrszn.analyticahousetestcase.R
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemTeamBinding

class TeamsAdapter(
    private val teamsList: ArrayList<TeamModel>,
    private val viewModel: TeamsViewModel
) :
    RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>() {

    class TeamsViewHolder(
        private val binding: ItemTeamBinding,
        private val viewModel: TeamsViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TeamModel) {
            binding.apply {
                team = item
                root.setOnClickListener {
                    val action =
                        TeamsFragmentDirections.actionTeamsFragmentToTeamDetailFragment(item.id)
                    it.findNavController().navigate(action)
                }
                addOrDeleteButton.setOnClickListener {
                    val favTeam = FavTeamModel(
                        item.id,
                        item.abbreviation,
                        item.city,
                        item.conference,
                        item.division,
                        item.fullName,
                        item.name
                    )
                    if (item.id == viewModel.getTeam(item.id)) {
                        viewModel.insertOrDeleteFavTeam(favTeam)
                        it.setBackgroundResource(R.drawable.ic_not_favorite)
                    } else {
                        viewModel.insertOrDeleteFavTeam(favTeam)
                        it.setBackgroundResource(R.drawable.ic_favorite)
                    }
                }
                if (item.id == viewModel.getTeam(item.id)) {
                    addOrDeleteButton.setBackgroundResource(R.drawable.ic_favorite)
                } else {
                    addOrDeleteButton.setBackgroundResource(R.drawable.ic_not_favorite)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val itemTeamBinding =
            ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamsViewHolder(itemTeamBinding, viewModel)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        val team = teamsList[position]
        holder.bind(team)
    }

    override fun getItemCount(): Int = teamsList.size
}