package com.mlhysrszn.analyticahousetestcase.ui.players

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mlhysrszn.analyticahousetestcase.R
import com.mlhysrszn.analyticahousetestcase.data.model.PlayerModel
import com.mlhysrszn.analyticahousetestcase.databinding.ItemPlayerBinding

class PlayersAdapter(
    private val playersList: ArrayList<PlayerModel>,
    private val viewModel: PlayersViewModel
) :
    RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>(), Filterable {

    var playersFilterList = ArrayList<PlayerModel>()

    init {
        playersFilterList = playersList
    }

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
                    val favPlayer = viewModel.favPlayerModel(item)
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
        val player = playersFilterList[position]
        holder.bind(player)
    }

    override fun getItemCount(): Int = playersFilterList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val searchText = p0.toString()
                playersFilterList = if (searchText.isEmpty()) {
                    playersList
                } else {
                    val resultsList = ArrayList<PlayerModel>()
                    for (row in playersList) {
                        if (row.firstName.lowercase().contains(searchText.lowercase())) {
                            resultsList.add(row)
                        }
                    }
                    resultsList
                }
                val filterResults = FilterResults()
                filterResults.values = playersFilterList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                playersFilterList = results?.values as ArrayList<PlayerModel>
                notifyDataSetChanged()
            }
        }
    }
}