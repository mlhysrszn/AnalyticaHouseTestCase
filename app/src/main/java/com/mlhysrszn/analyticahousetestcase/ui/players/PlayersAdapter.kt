package com.mlhysrszn.analyticahousetestcase.ui.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemPlayerBinding

class PlayersAdapter(private val playersList: ArrayList<PlayerModel>) :
    RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    class PlayersViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlayerModel) {
            binding.apply {
                player = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersViewHolder {
        val itemPlayerBinding =
            ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayersViewHolder(itemPlayerBinding)
    }

    override fun onBindViewHolder(holder: PlayersViewHolder, position: Int) {
        val player = playersList[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int = playersList.size
}