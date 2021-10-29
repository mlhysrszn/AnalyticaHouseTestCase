package com.mlhysrszn.analyticahousetestcase.ui.detailplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mlhysrszn.analyticahousetestcase.R
import com.mlhysrszn.analyticahousetestcase.data.model.FavPlayerModel
import com.mlhysrszn.analyticahousetestcase.data.model.FavTeamModel
import com.mlhysrszn.analyticahousetestcase.databinding.FragmentPlayerDetailBinding

class PlayerDetailFragment : Fragment() {
    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlayerDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val playerId = PlayerDetailFragmentArgs.fromBundle(it).playerId
            viewModel.getPlayer(playerId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.player.observe(viewLifecycleOwner, {
            binding.player = it
            if (it.id == viewModel.getPlayerId(it.id)) {
                binding.addOrDeleteButton.setBackgroundResource(R.drawable.ic_favorite)
            }
            else {
                binding.addOrDeleteButton.setBackgroundResource(R.drawable.ic_not_favorite)
            }
            binding.addOrDeleteButton.setOnClickListener { button ->
                val favPlayer = FavPlayerModel(
                    it.id,
                    it.firstName,
                    it.heightFeet,
                    it.heightInches,
                    it.lastName,
                    it.position,
                    FavTeamModel(
                        it.team.id,
                        it.team.abbreviation,
                        it.team.city,
                        it.team.conference,
                        it.team.division,
                        it.team.fullName,
                        it.team.name
                    ),
                    it.weightPounds
                )
                if (it.id == viewModel.getPlayerId(it.id)) {
                    viewModel.insertOrDeleteFavPlayer(favPlayer)
                    button.setBackgroundResource(R.drawable.ic_not_favorite)
                } else {
                    viewModel.insertOrDeleteFavPlayer(favPlayer)
                    button.setBackgroundResource(R.drawable.ic_favorite)
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, {
            if (it == true) {
                binding.progressBar.visibility = View.VISIBLE
                binding.txtTeam.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.txtFirstName.visibility = View.VISIBLE
                binding.txtLastName.visibility = View.VISIBLE
                binding.txtPosition.visibility = View.VISIBLE
                binding.txtHeightInches.visibility = View.VISIBLE
                binding.txtTeam.visibility = View.VISIBLE
                binding.txtAbbreviation.visibility = View.VISIBLE
                binding.txtCity.visibility = View.VISIBLE
                binding.txtConference.visibility = View.VISIBLE
                binding.txtDivision.visibility = View.VISIBLE
                binding.txtFullName.visibility = View.VISIBLE
                binding.txtName.visibility = View.VISIBLE
                binding.addOrDeleteButton.visibility = View.VISIBLE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}