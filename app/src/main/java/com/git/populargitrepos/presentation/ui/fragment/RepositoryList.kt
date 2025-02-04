package com.git.populargitrepos.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.git.populargitrepos.databinding.RepositorylistBinding
import com.git.populargitrepos.presentation.viewmodel.GithubViewModel
import com.git.populargitrepos.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "RepositoryList"
@AndroidEntryPoint
class RepositoryList : Fragment() {

    private val binding by lazy { RepositorylistBinding.inflate(layoutInflater) }
    private val viewmodel by viewModels<GithubViewModel>()

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

        lifecycleScope.launch {
            viewmodel.repositoryState.collectLatest {
                when(it){
                    is NetworkResult.Success -> {
                        Log.d(TAG, "onViewCreated: ${it.data}")
                        Toast.makeText(requireContext(), it.data.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Loading -> {
                        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
                    }
                    is NetworkResult.Empty -> {

                    }
                    is NetworkResult.Error -> {
                        Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}