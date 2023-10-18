package com.girogevoro.redditapp.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.girogevoro.redditapp.databinding.ItemPostBinding

class PostViewHolder(
    private val binding: ItemPostBinding,
) :
    RecyclerView.ViewHolder(binding.root),
    PostItemView {
    override val pos: Int = -1

    override fun setTitle(title: String) {
        binding.title.text = title
    }

    override fun setRating(rating: String) {
        binding.rating.text = rating
    }

    override fun setCommentCount(commentCount: String) {
        binding.commentCount.text = commentCount
    }
}