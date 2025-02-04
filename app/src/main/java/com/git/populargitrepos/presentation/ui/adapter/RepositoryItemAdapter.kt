package com.git.populargitrepos.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.git.populargitrepos.databinding.RepositoryItemBinding
import com.git.populargitrepos.domain.model.Item
import com.git.populargitrepos.presentation.ui.viewholder.RepositoryItemVH
import javax.inject.Inject

class RepositoryItemAdapter @Inject constructor() : ListAdapter<Item, RepositoryItemVH>(DIFFUTILS) {

    companion object{
        val DIFFUTILS = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryItemVH {
        val l = LayoutInflater.from(parent.context)
        val v = RepositoryItemBinding.inflate(l, parent, false)
        return RepositoryItemVH(v)
    }

    override fun onBindViewHolder(holder: RepositoryItemVH, position: Int) {
        holder.binding.apply {
            item = getItem(position)
        }
    }
}