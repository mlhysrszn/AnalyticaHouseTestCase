package com.mlhysrszn.analyticahousetestcase.ui.detailteam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mlhysrszn.analyticahousetestcase.R
import com.mlhysrszn.analyticahousetestcase.databinding.FragmentTeamDetailBinding

class TeamDetailFragment : Fragment() {
    private var _binding: FragmentTeamDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val teamId = TeamDetailFragmentArgs.fromBundle(it).teamId
            viewModel.getTeam(teamId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.team.observe(viewLifecycleOwner, {
            binding.team = it
            if (it.id == viewModel.getTeamId(it.id)) {
                binding.addOrDeleteButton.setBackgroundResource(R.drawable.ic_favorite)
            } else {
                binding.addOrDeleteButton.setBackgroundResource(R.drawable.ic_not_favorite)
            }
            binding.addOrDeleteButton.setOnClickListener { button ->
                val favTeam = viewModel.favTeamModel(it)
                if (it.id == viewModel.getTeamId(it.id)) {
                    viewModel.insertOrDeleteFavTeam(favTeam)
                    button.setBackgroundResource(R.drawable.ic_not_favorite)
                } else {
                    viewModel.insertOrDeleteFavTeam(favTeam)
                    button.setBackgroundResource(R.drawable.ic_favorite)
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it == false) {
                binding.progressBar.visibility = View.GONE
                binding.teamDetailLinear.visibility = View.VISIBLE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}