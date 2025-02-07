package com.git.populargitrepos.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.git.populargitrepos.databinding.RepositoryDetailsBinding
import com.git.populargitrepos.presentation.adapter.TopicAdapter
import com.git.populargitrepos.utils.TimeDateUtils
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "RepositoryDetails"
@AndroidEntryPoint
class RepositoryDetails : Fragment() {

    private val binding by lazy { RepositoryDetailsBinding.inflate(layoutInflater) }
    private val args by navArgs<RepositoryDetailsArgs>()
    @Inject lateinit var topicAdapter: TopicAdapter

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

    private fun initView(){
        val mLayoutManager = FlexboxLayoutManager(requireContext())
        mLayoutManager.apply {
            flexDirection = FlexDirection.ROW
        }

        binding.apply {
            TopicRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = mLayoutManager
                adapter = topicAdapter
            }
            item = args.repository
            createDate = TimeDateUtils.convertUtcToLocalTime(args.repository.created_at)
            publishDate = TimeDateUtils.convertUtcToLocalTime(args.repository.pushed_at)
            updateDate = TimeDateUtils.convertUtcToLocalTime(args.repository.updated_at)

            topicAdapter.submitList(args.repository.topics)
            Log.d(TAG, "initView: topic ${args.repository.topics}")
        }
    }


}