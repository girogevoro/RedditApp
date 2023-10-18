package com.girogevoro.redditapp.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.girogevoro.redditapp.domian.entity.PostData

class PostsDiffCallBack : DiffUtil.ItemCallback<PostData>() {
    override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem == newItem
    }
}