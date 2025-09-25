package com.example.globalnews.data.network

class NewsRepository {

    private val apiService = RetrofitInstance.api

    suspend fun getTopHeadlines(country: String, apiKey: String) =
        apiService.getTopHeadlines(country, apiKey)

    suspend fun getNews(query: String, apiKey: String) =
        apiService.getNews(query, apiKey)

    suspend fun getAvailableSources(apiKey: String) =
        apiService.getAvailableSources(apiKey)
}