package com.orange.nikoolin.reddit.data.models

import com.google.gson.annotations.SerializedName

data class BaseResponse(
        @SerializedName("data")
        val body: PostWrappersHolder)




