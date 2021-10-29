package com.mlhysrszn.analyticahousetestcase.ui.players

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mlhysrszn.analyticahousetestcase.databinding.FragmentPlayersBinding

class PlayersFragment : Fragment() {
    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlayersViewModel by viewModels()
    private lateinit var adapter: PlayersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.playersList.observe(viewLifecycleOwner, {
            adapter = PlayersAdapter(it, viewModel)
            binding.rvPlayers.adapter = adapter
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
                binding.rvPlayers.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.rvPlayers.visibility = View.VISIBLE
            }
        })

        binding.searchPlayer.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}