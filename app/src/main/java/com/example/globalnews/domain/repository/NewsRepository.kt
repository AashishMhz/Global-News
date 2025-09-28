package com.example.globalnews.domain.repository

import com.example.globalnews.domain.model.NewsResponse

interface NewsRepository {
    suspend fun getTopHeadlines(country: String): NewsResponse
    suspend fun getNews(query: String): NewsResponse
    suspend fun getAvailableSources(): NewsResponse
}