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
import com.git.populargitrepos.presentation.ui.adapter.RepositoryItemAdapter
import com.git.populargitrepos.presentation.viewmodel.GithubViewModel
import com.git.populargitrepos.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "RepositoryList"
@AndroidEntryPoint
class RepositoryList : Fragment() {

    private val binding by lazy { RepositorylistBinding.inflate(layoutInflater) }
    private val viewmodel by viewModels<GithubViewModel>()
    @Inject lateinit var repositoryItemAdapter: RepositoryItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getRepositoryList()
        initView()
    }

    private fun initView(){
        binding.apply {
            RecyclerView.apply {
                setHasFixedSize(true)
                adapter = repositoryItemAdapter
            }
        }
    }

    private fun getRepositoryList(){
        lifecycleScope.launch {
            viewmodel.repositoryState.collectLatest {
                when(it){
                    is NetworkResult.Success -> {
                        repositoryItemAdapter.submitList(it.data?.items)
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