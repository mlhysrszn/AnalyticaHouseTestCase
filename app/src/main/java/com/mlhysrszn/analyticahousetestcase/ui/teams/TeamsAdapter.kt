package com.mlhysrszn.analyticahousetestcase.ui.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.analyticahousetestcase.R
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel
import com.mlhysrszn.analyticahousetestcase.data.model.TeamModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemTeamBinding

class TeamsAdapter(
    private val teamsList: ArrayList<TeamModel>,
    private val viewModel: TeamsViewModel
) :
    RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder>(), Filterable {

    var teamsFilterList = ArrayList<TeamModel>()

    init {
        teamsFilterList = teamsList
    }

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
        val team = teamsFilterList[position]
        holder.bind(team)
    }

    override fun getItemCount(): Int = teamsFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val searchText = p0.toString()
                teamsFilterList = if (searchText.isEmpty()) {
                    teamsList
                } else {
                    val resultsList = ArrayList<TeamModel>()
                    for (row in teamsList) {
                        if (row.fullName.lowercase().contains(searchText.lowercase()) or
                            row.abbreviation.lowercase().contains(searchText.lowercase())
                        ) {
                            resultsList.add(row)
                        }
                    }
                    resultsList
                }
                val filterResults = FilterResults()
                filterResults.values = teamsFilterList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                teamsFilterList = results?.values as ArrayList<TeamModel>
                notifyDataSetChanged()
            }

        }
    }
}