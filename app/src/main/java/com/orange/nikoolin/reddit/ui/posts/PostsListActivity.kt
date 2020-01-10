package com.orange.nikoolin.reddit.ui.posts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.orange.nikoolin.reddit.BR
import com.orange.nikoolin.reddit.R
import com.orange.nikoolin.reddit.databinding.ActivityPostsListBinding
import com.orange.nikoolin.reddit.ui.base.BaseActivity
import com.orange.nikoolin.reddit.ui.posts.adapter.PostAdapter

class PostsListActivity : BaseActivity<ActivityPostsListBinding, PostsListViewModel>() {

    companion object {
        const val KEY_THREAD_HANDLER_TYPE = "thread_handler_type"
        fun init(context: Context, type: ThreadManagerType): Intent {
            val intent = Intent(context, PostsListActivity::class.java)
            intent.putExtra(KEY_THREAD_HANDLER_TYPE, type.ordinal)
            return intent
        }
    }

    override fun getViewModelClass(): Class<PostsListViewModel> = PostsListViewModel::class.java

    override fun layoutId(): Int {
        return R.layout.activity_posts_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        when (ThreadManagerType.values()[intent.getIntExtra(KEY_THREAD_HANDLER_TYPE, 0)]) {
            ThreadManagerType.COROUTINES -> {
                viewModel.loadDataCoroutine()
            }
            ThreadManagerType.RXJAVA -> {
                viewModel.loadDataRx()
            }
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.setVariable(BR.postAdapter, PostAdapter())

        viewModel.dataPosts.observe(this, Observer { it ->
            it?.let {
                binding.postAdapter?.submitList(it)
            }
        })
    }

    override fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    enum class ThreadManagerType {
        COROUTINES,
        RXJAVA
    }
}
