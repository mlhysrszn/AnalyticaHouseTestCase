package com.mlhysrszn.analyticahousetestcase.ui.detailplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mlhysrszn.analyticahousetestcase.databinding.FragmentPlayerDetailBinding

class PlayerDetailFragment : Fragment() {
    private var _binding: FragmentPlayerDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val player = PlayerDetailFragmentArgs.fromBundle(it).player
            binding.player = player
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}