package com.orange.nikoolin.reddit.data.services

import com.orange.nikoolin.reddit.data.models.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoroutineApiService {
    @GET("/top.json")
    suspend fun getTopPosts(
            @Query("limit") limit: Int
    ): Response<BaseResponse>
}