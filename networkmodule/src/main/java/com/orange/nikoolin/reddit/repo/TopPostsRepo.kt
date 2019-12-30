package com.orange.nikoolin.reddit.repo

import com.orange.nikoolin.reddit.NetworkResult
import com.orange.nikoolin.reddit.data.models.BaseResponse
import com.orange.nikoolin.reddit.data.services.CoroutineApiService

class TopPostsRepo constructor(private val coroutineApiService: CoroutineApiService) {

    suspend fun getTopPosts(limit: Int): NetworkResult<BaseResponse> {
        val response = coroutineApiService.getTopPosts(limit)
        if (response.isSuccessful) {
            val data = response.body()
            if (data != null) {
                return NetworkResult.Success(data)
            }
        }
        return NetworkResult.Failure(response)
    }
}