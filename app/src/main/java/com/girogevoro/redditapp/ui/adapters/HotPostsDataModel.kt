package com.girogevoro.redditapp.ui.adapters

import com.girogevoro.redditapp.domian.entity.PostData

interface HotPostsDataModel {
    fun bindView(view: PostItemView, data: PostData?)
}