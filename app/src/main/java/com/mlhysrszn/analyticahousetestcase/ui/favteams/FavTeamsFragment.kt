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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.favTeamsList.observe(viewLifecycleOwner, {
            val adapter = it?.let { it1 -> FavTeamsAdapter(it1, viewModel) }
            binding.rvFavTeams.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}