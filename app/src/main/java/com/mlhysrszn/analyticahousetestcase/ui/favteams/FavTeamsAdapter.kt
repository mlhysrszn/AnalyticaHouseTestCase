package com.mlhysrszn.analyticahousetestcase.ui.favteams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemFavTeamBinding
import com.mlhysrszn.analyticahousetestcase.ui.favorites.FavoritesFragmentDirections

class FavTeamsAdapter(
    private val favTeamsList: List<FavTeamModel>,
    private val viewModel: FavTeamsViewModel
) :
    RecyclerView.Adapter<FavTeamsAdapter.FavTeamsViewHolder>() {

    class FavTeamsViewHolder(
        private val binding: ItemFavTeamBinding,
        private val viewModel: FavTeamsViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavTeamModel) {
            binding.apply {
                team = item
                root.setOnClickListener {
                    val action =
                        FavoritesFragmentDirections.actionFavoritesFragmentToTeamDetailFragment(
                            item.teamId
                        )
                    it.findNavController().navigate(action)
                }
                deleteButton.setOnClickListener {
                    viewModel.deleteFavTeam(item.teamId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavTeamsViewHolder {
        val itemFavTeamBinding =
            ItemFavTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavTeamsViewHolder(itemFavTeamBinding, viewModel)
    }

    override fun onBindViewHolder(holder: FavTeamsViewHolder, position: Int) {
        val favTeam = favTeamsList[position]
        holder.bind(favTeam)
    }

    override fun getItemCount(): Int = favTeamsList.size
}