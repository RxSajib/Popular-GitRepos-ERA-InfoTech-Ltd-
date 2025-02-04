package com.git.populargitrepos.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.git.populargitrepos.databinding.RepositorylistBinding


class RepositoryList : Fragment() {

    private val binding by lazy { RepositorylistBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            Btn.setOnClickListener {
                RepositoryListDirections.actionRepositoryListToRepositoryDetails().apply {
                    findNavController().navigate(this)
                }
            }
        }
    }
}