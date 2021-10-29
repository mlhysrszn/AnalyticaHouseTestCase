package com.mlhysrszn.analyticahousetestcase.ui.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mlhysrszn.analyticahousetestcase.R
import com.mlhysrszn.analyticahousetestcase.data.model.FavPlayerModel
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemPlayerBinding

class PlayersAdapter(
    private val playersList: ArrayList<PlayerModel>,
    private val viewModel: PlayersViewModel
) :
    RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    class PlayersViewHolder(
        private val binding: ItemPlayerBinding,
        private val viewModel: PlayersViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlayerModel) {
            binding.apply {
                player = item
                root.setOnClickListener {
                    val action =
                        PlayersFragmentDirections.actionPlayersFragmentToPlayerDetailFragment(item.id)
                    it.findNavController().navigate(action)
                }
                addOrDeleteButton.setOnClickListener {
                    val favPlayer = FavPlayerModel(
                        item.id,
                        item.firstName,
                        item.heightFeet,
                        item.heightInches,
                        item.lastName,
                        item.position,
                        FavTeamModel(
                            item.team.id,
                            item.team.abbreviation,
                            item.team.city,
                            item.team.conference,
                            item.team.division,
                            item.team.fullName,
                            item.team.name
                        ),
                        item.weightPounds
                    )
                    if (item.id == viewModel.getPlayer(item.id)) {
                        viewModel.insertOrDeleteFavPlayer(favPlayer)
                        it.setBackgroundResource(R.drawable.ic_not_favorite)
                    } else {
                        viewModel.insertOrDeleteFavPlayer(favPlayer)
                        it.setBackgroundResource(R.drawable.ic_favorite)
                    }
                }
                if (item.id == viewModel.getPlayer(item.id)) {
                    addOrDeleteButton.setBackgroundResource(R.drawable.ic_favorite)
                } else {
                    addOrDeleteButton.setBackgroundResource(R.drawable.ic_not_favorite)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val itemPlayerBinding =
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayersViewHolder(itemPlayerBinding, viewModel)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = playersList[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int = playersList.size
}