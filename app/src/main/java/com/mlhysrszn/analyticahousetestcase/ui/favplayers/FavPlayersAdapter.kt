package com.mlhysrszn.analyticahousetestcase.ui.favplayers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.analyticahousetestcase.data.model.FavPlayerModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemFavPlayerBinding
import com.mlhysrszn.analyticahousetestcase.ui.favorites.FavoritesFragmentDirections

class FavPlayersAdapter(
    private val favPlayersList: List<FavPlayerModel>,
    private val viewModel: FavPlayersViewModel
) :
    RecyclerView.Adapter<FavPlayersAdapter.FavPlayersViewHolder>() {

    class FavPlayersViewHolder(
        val binding: ItemFavPlayerBinding,
        private val viewModel: FavPlayersViewModel
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavPlayerModel) {
            binding.apply {
                player = item
                root.setOnClickListener {
                    val action =
                        FavoritesFragmentDirections.actionFavoritesFragmentToPlayerDetailFragment(
                            item.playerId
                        )
                    Navigation.findNavController(it).navigate(action)
                }
                deleteButton.setOnClickListener {
                    viewModel.deleteFavPlayer(item.playerId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavPlayersViewHolder {
        val itemFavPlayerBinding =
            ItemFavPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavPlayersViewHolder(itemFavPlayerBinding, viewModel)
    }

    override fun onBindViewHolder(holder: FavPlayersViewHolder, position: Int) {
        val favPlayer = favPlayersList[position]
        holder.bind(favPlayer)
    }

    override fun getItemCount(): Int = favPlayersList.size
}