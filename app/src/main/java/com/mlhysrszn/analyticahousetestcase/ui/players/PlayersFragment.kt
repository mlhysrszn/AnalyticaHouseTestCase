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
            val adapter = PlayersAdapter(it, viewModel)
            binding.rvPlayers.adapter = adapter
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}