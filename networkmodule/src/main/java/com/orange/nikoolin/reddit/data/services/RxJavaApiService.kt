package com.orange.nikoolin.reddit.data.services

import com.orange.nikoolin.reddit.data.models.BaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RxJavaApiService {
    @GET("/top.json")
    fun getTopPosts(
            @Query("limit") limit: Int
    ): Single<BaseResponse>
}