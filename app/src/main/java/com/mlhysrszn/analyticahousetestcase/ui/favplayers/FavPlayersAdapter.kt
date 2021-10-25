package com.mlhysrszn.analyticahousetestcase.ui.favplayers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemPlayerBinding

class FavPlayersAdapter(private val favPlayersList: ArrayList<PlayerModel>) :
    RecyclerView.Adapter<FavPlayersAdapter.FavPlayersViewHolder>() {

    class FavPlayersViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlayerModel) {
            binding.apply {
                player = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavPlayersViewHolder {
        val itemPlayerBinding =
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavPlayersViewHolder(itemPlayerBinding)
    }

    override fun onBindViewHolder(holder: FavPlayersViewHolder, position: Int) {
        val favPlayer = favPlayersList[position]
        holder.bind(favPlayer)
    }

    override fun getItemCount(): Int = favPlayersList.size
}