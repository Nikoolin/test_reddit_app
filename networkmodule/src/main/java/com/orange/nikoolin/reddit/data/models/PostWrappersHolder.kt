package com.orange.nikoolin.reddit.data.models

import com.google.gson.annotations.SerializedName

data class PostWrappersHolder(
        @SerializedName("children")
        val children: ArrayList<out PostWrapper>)