package com.orange.nikoolin.reddit.data.models

import com.google.gson.annotations.SerializedName

data class Post(
        @SerializedName("id")
        val id: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("author")
        val author: String,
        @SerializedName("subreddit")
        val subreddit: String,
        @SerializedName("created_utc")
        val createdUtc: Long,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("num_comments")
        val numComments: Int,
        @SerializedName("score")
        val score: Int,
        @SerializedName("url")
        val url: String)