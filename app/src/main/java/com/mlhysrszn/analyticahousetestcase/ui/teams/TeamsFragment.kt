package com.mlhysrszn.analyticahousetestcase.ui.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mlhysrszn.analyticahousetestcase.databinding.FragmentTeamsBinding

class TeamsFragment : Fragment() {
    private var _binding: FragmentTeamsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamsViewModel by viewModels()
    private lateinit var adapter: TeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.teamsList.observe(viewLifecycleOwner, {
            adapter = TeamsAdapter(it, viewModel)
            binding.rvTeams.adapter = adapter
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
                binding.rvTeams.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.rvTeams.visibility = View.VISIBLE
            }
        })

        binding.searchTeam.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
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