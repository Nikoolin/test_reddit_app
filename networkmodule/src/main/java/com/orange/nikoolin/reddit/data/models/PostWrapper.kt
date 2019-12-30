package com.orange.nikoolin.reddit.data.models

import com.google.gson.annotations.SerializedName

data class PostWrapper(
        @SerializedName("data")
        val body: Post)