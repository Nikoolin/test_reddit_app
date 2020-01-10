package com.orange.nikoolin.reddit.ui.posts.adapter

import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.format.DateUtils
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.orange.nikoolin.reddit.R
import com.orange.nikoolin.reddit.data.models.Post
import com.orange.nikoolin.reddit.databinding.PostsListItemBinding
import java.util.concurrent.TimeUnit

class PostsViewHolder(
        private val binding: PostsListItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val glide: RequestManager = Glide.with(binding.root.context)

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
            postTitle.text = post?.title ?: ""
            authorName.text = itemView.context.resources.getString(R.string.author_name,
                    post?.author ?: "")
            postSubreddit.text = itemView.context.resources.getString(R.string.subreddit,
                    post?.subreddit ?: "")
            postScore.text = "${post?.score ?: 0}"
            val time = post?.createdUtc ?: 0
            createdTime.text = DateUtils.getRelativeTimeSpanString(TimeUnit.SECONDS.toMillis(time))
            comments.text = "${post?.numComments ?: 0}"
            thumbnailImageView.visibility = View.VISIBLE
            glide.load(post?.thumbnail ?: android.R.drawable.stat_notify_error)
                    .centerCrop()
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .addListener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            thumbnailImageView.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            return false
                        }
                    })
                    .into(thumbnailImageView)
        }
    }
}