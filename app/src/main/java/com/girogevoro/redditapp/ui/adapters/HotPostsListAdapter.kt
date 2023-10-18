package com.girogevoro.redditapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.girogevoro.redditapp.databinding.ItemPostBinding
import com.girogevoro.redditapp.domian.entity.PostData

class HotPostsListAdapter(
    private val dataModel: HotPostsDataModel,
) : PagingDataAdapter<PostData, PostViewHolder>(PostsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holderMarker: PostViewHolder, position: Int) =
        dataModel.bindView(holderMarker, getItem(position))
}