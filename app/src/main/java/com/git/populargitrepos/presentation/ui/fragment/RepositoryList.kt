package com.git.populargitrepos.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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
    @Inject
    lateinit var repositoryItemAdapter: dagger.Lazy<RepositoryItemAdapter> //todo Lazy injection

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {

        //todo handle click event repository item
        val _adapter = repositoryItemAdapter.get().apply {
            onItemClick = { item ->
                item.let {
                    //todo Navigate to details page
                    val action = RepositoryListDirections.actionRepositoryListToRepositoryDetails(
                        repository = item
                    )
                    findNavController().navigate(action)
                }
            }
        }

        binding.apply {
            RecyclerView.apply {
                setHasFixedSize(true)
                adapter = _adapter
            }
        }

        getRepositoryList(_adapter)
    }

    private fun getRepositoryList(_adapter: RepositoryItemAdapter) {
        binding.apply {
            ReloadIcon.setOnClickListener {
                viewmodel.getRepositories()
            }
            lifecycleScope.launch {
                viewmodel.repositoryState.collectLatest {
                    when (it) {
                        is NetworkResult.Success -> {
                            _adapter.submitList(it.data?.items)
                            ErrorIcon.isVisible = false
                            ErrorMessage.isVisible = false
                            ReloadIcon.isVisible = false
                            RecyclerView.isVisible = true
                            ProgressBar.isVisible = false
                        }

                        is NetworkResult.Loading -> {
                            ErrorIcon.isVisible = false
                            ErrorMessage.isVisible = false
                            ReloadIcon.isVisible = false
                            RecyclerView.isVisible = false
                            ProgressBar.isVisible = true
                        }

                        is NetworkResult.Empty -> {

                        }

                        is NetworkResult.Error -> {
                            ErrorIcon.isVisible = true
                            ErrorMessage.isVisible = true
                            ReloadIcon.isVisible = true
                            RecyclerView.isVisible = false
                            ProgressBar.isVisible = false
                            errorMessage = it.message
                        }
                    }
                }
            }
        }

    }
}