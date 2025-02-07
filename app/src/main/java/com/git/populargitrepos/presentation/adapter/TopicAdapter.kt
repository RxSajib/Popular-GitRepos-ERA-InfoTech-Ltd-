package com.git.populargitrepos.presentation.adapter

import android.adservices.topics.Topic
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.git.populargitrepos.databinding.TopicItemBinding
import com.git.populargitrepos.presentation.view_holder.TopicVH
import javax.inject.Inject

class TopicAdapter @Inject constructor() : ListAdapter<String, TopicVH>(DIFFUTILS) {

    companion object{
        val DIFFUTILS = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicVH {
        val l = LayoutInflater.from(parent.context)
        val v = TopicItemBinding.inflate(l, parent, false)
        return TopicVH(v)
    }

    override fun onBindViewHolder(holder: TopicVH, position: Int) {
        holder.binding.apply {
            topic = getItem(position)
        }
    }
}