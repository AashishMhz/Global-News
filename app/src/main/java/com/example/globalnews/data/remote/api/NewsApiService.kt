package com.example.globalnews.data.remote.api

import com.example.globalnews.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET(value = "top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NewsResponseDto

    @GET(value = "everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NewsResponseDto

    @GET(value = "sources")
    suspend fun getAvailableSources(
        @Query("apiKey") apiKey: String
    ): NewsResponseDto
}