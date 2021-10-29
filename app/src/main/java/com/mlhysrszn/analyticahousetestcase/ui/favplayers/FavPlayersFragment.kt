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

        viewModel.favPlayersList.observe(viewLifecycleOwner, {
            val adapter = it?.let { it1 -> FavPlayersAdapter(it1, viewModel) }
            binding.rvFavPlayers.adapter = adapter
            println("SADASD")
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}