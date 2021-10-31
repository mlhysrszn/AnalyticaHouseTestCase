package com.mlhysrszn.analyticahousetestcase.ui.favplayers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mlhysrszn.analyticahousetestcase.databinding.FragmentFavPlayersBinding

class FavPlayersFragment : Fragment() {
    private var _binding: FragmentFavPlayersBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: FavPlayersAdapter
    private val viewModel: FavPlayersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFavPlayersList()
        viewModel.favPlayersList.observe(viewLifecycleOwner, {
            adapter = FavPlayersAdapter(it,viewModel)
            binding.rvFavPlayers.adapter = adapter
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}