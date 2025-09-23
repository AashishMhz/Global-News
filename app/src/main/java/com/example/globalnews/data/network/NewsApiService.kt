package com.example.globalnews.data.network

import com.example.globalnews.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET(value = "top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = "a13c0be8ab5e43e082a02d3e0d449785"
    ): NewsResponse

    @GET(value = "everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = "a13c0be8ab5e43e082a02d3e0d449785"
    ): NewsResponse
}