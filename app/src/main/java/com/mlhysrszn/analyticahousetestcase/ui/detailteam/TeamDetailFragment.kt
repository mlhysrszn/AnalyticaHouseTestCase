package com.mlhysrszn.analyticahousetestcase.ui.detailteam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mlhysrszn.analyticahousetestcase.R
import com.mlhysrszn.analyticahousetestcase.databinding.FragmentTeamDetailBinding

class TeamDetailFragment : Fragment() {
    private var _binding: FragmentTeamDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val team = TeamDetailFragmentArgs.fromBundle(it).team
            binding.team = team
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}