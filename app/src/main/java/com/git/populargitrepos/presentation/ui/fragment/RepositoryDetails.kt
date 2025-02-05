package com.git.populargitrepos.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.git.populargitrepos.databinding.RepositoryDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "RepositoryDetails"
@AndroidEntryPoint
class RepositoryDetails : Fragment() {

    private val binding by lazy { RepositoryDetailsBinding.inflate(layoutInflater) }
    private val args by navArgs<RepositoryDetailsArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "onViewCreated: args $args")
    }


}