package com.git.populargitrepos.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.git.populargitrepos.databinding.RepositoryItemBinding
import com.git.populargitrepos.domain.model.Item
import com.git.populargitrepos.presentation.ui.viewholder.RepositoryItemVH
import javax.inject.Inject

class RepositoryItemAdapter @Inject constructor(
) : ListAdapter<Item, RepositoryItemVH>(DIFFUTILS) {

    var onItemClick: ((Item) -> Unit)? = null //todo Nullable click listener

    companion object {
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
            if (holder.bindingAdapterPosition != RecyclerView.NO_POSITION) {
                root.setOnClickListener {
                    item?.let { onItemClick?.invoke(it) }  //todo Set click listener on root view
                }
            }
        }
    }
}