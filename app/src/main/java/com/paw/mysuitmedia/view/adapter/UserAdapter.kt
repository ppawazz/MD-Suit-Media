package com.paw.mysuitmedia.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.paw.mysuitmedia.databinding.ItemUserBinding
import com.paw.mysuitmedia.model.remote.response.DataItem

class UserAdapter(
    private val listener: OnItemClickListener
) : PagingDataAdapter<DataItem, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            Glide.with(binding.root.context)
                .load(user.avatar)
                .into(binding.ivUser)

            binding.tvItemName.text = "${user.firstName} ${user.lastName}"
            binding.tvEmail.text = user.email

            binding.root.setOnClickListener {
                listener.onItemClick(user)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(user: DataItem)
    }

    private class UserDiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }
}
