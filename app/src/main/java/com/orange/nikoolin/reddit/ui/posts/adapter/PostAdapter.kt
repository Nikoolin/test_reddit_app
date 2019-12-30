package com.orange.nikoolin.reddit.ui.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.orange.nikoolin.reddit.data.models.Post
import com.orange.nikoolin.reddit.databinding.PostsListItemBinding


/**
 * Adapter for the [RecyclerView] in [PostsListActivity].
 */
class PostAdapter(private val glide: RequestManager) : ListAdapter<Post, RecyclerView.ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostsViewHolder(PostsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false), glide)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = getItem(position)
        (holder as PostsViewHolder).bind(post)
    }
}

private class PostDiffCallback : DiffUtil.ItemCallback<Post>() {

    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}