package com.mlhysrszn.analyticahousetestcase.ui.detailplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mlhysrszn.analyticahousetestcase.R
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
            } else {
                binding.addOrDeleteButton.setBackgroundResource(R.drawable.ic_not_favorite)
            }
            binding.addOrDeleteButton.setOnClickListener { button ->
                val favPlayer = viewModel.favPlayerModel(it)
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
            if (!it) {
                binding.progressBar.visibility = View.GONE
                binding.playerDetailLinear.visibility = View.VISIBLE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}