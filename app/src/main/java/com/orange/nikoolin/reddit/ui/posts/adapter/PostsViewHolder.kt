package com.orange.nikoolin.reddit.ui.posts.adapter

import android.net.Uri
import android.text.format.DateUtils
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.orange.nikoolin.reddit.R
import com.orange.nikoolin.reddit.data.models.Post
import com.orange.nikoolin.reddit.databinding.PostsListItemBinding

class PostsViewHolder(
        private val binding: PostsListItemBinding,
        private val glide: RequestManager
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.setClickListener {
            binding.post?.let { post ->
                CustomTabsIntent.Builder()
                        .addDefaultShareMenuItem()
                        .setToolbarColor(ContextCompat.getColor(it.context, R.color.colorPrimary))
                        .setStartAnimations(it.context, R.anim.slide_right_in, R.anim.slide_left_out)
                        .setExitAnimations(it.context, R.anim.slide_left_in, R.anim.slide_right_out)
                        .setShowTitle(true)
                        .build().launchUrl(it.context, Uri.parse(post.url))
            }
        }
    }

    fun bind(item: Post?) {
        binding.apply {
            post = item
            postTitle.text = post?.title ?: "unknown"
            authorName.text = itemView.context.resources.getString(R.string.author_name,
                    post?.author ?: "unknown")
            postSubreddit.text = itemView.context.resources.getString(R.string.subreddit,
                    post?.subreddit ?: "unknown")
            postScore.text = "${post?.score ?: 0}"
            val time = post?.createdUtc ?: 0
            createdTime.text = DateUtils.getRelativeTimeSpanString(time * 1000)
            comments.text = "${post?.numComments ?: 0}"
            if (post?.thumbnail?.startsWith("http") == true) {
                thumbnailImageView.visibility = View.VISIBLE
                glide.load(post?.thumbnail ?: android.R.drawable.stat_notify_error)
                        .centerCrop()
                        .placeholder(android.R.drawable.ic_menu_gallery)
                        .into(thumbnailImageView)
            } else {
                thumbnailImageView.visibility = View.GONE
                glide.clear(thumbnailImageView)
            }
        }
    }
}