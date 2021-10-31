package com.mlhysrszn.analyticahousetestcase.ui.favteams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mlhysrszn.analyticahousetestcase.databinding.FragmentFavTeamsBinding

class FavTeamsFragment : Fragment() {
    private var _binding: FragmentFavTeamsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavTeamsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFavTeamsList()
        viewModel.favTeamsList.observe(viewLifecycleOwner, {
            val adapter = FavTeamsAdapter(it, viewModel)
            binding.rvFavTeams.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}