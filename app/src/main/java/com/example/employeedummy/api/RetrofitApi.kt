package com.example.employeedummy.api

import com.example.employeedummy.models.PostList
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/posts")
    suspend fun getAllPosts(): Response<PostList>
}