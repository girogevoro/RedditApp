package com.girogevoro.redditapp.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.girogevoro.redditapp.R

import com.girogevoro.redditapp.databinding.FragmentMainBinding
import com.girogevoro.redditapp.domian.entity.PostData
import com.girogevoro.redditapp.ui.adapters.HotPostsDataModel
import com.girogevoro.redditapp.ui.adapters.HotPostsListAdapter
import com.girogevoro.redditapp.ui.adapters.PostItemView
import com.girogevoro.redditapp.util.ViewBindingFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : ViewBindingFragment<FragmentMainBinding>() {

    private val viewModel1: MainViewModel by viewModel()
    private var adapter: HotPostsListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        collectUiState()
    }

    private fun initList() = with(binding) {
        hotPostsListRv.layoutManager = LinearLayoutManager(requireContext())
        adapter = HotPostsListAdapter(
            hotPostsDataModel
        )
        hotPostsListRv.adapter = adapter
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel1.getHotPosts().collectLatest { posts ->
                adapter?.submitData(posts)
            }
        }
    }

    private val hotPostsDataModel = object : HotPostsDataModel {
        override fun bindView(view: PostItemView, data: PostData?) {
            data?.let {
                view.setTitle(it.title)
                view.setRating(it.upVoted.toString())
                view.setCommentCount(it.commentsCount.toString())
            } ?: run {
                view.setTitle(getString(R.string.loading))
                view.setRating(getString(R.string.undefined))
                view.setCommentCount(getString(R.string.undefined))
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment()
    }
}