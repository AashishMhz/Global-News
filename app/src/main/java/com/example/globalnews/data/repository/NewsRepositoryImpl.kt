package com.example.globalnews.data.repository

import com.example.globalnews.BuildConfig
import com.example.globalnews.data.remote.api.NewsApiService
import com.example.globalnews.domain.model.NewsResponse
import com.example.globalnews.domain.repository.NewsRepository
import com.example.globalnews.utils.toDomain


class NewsRepositoryImpl(
    private val apiService: NewsApiService
) : NewsRepository {

    private val apiKey: String get() = BuildConfig.NEWS_API_KEY

        override suspend fun getTopHeadlines(country: String): NewsResponse {
            return apiService.getTopHeadlines(country, apiKey).toDomain()
        }

    override suspend fun getNews(query: String): NewsResponse {
        return apiService.getNews(query, apiKey).toDomain()
    }

    override suspend fun getAvailableSources(): NewsResponse {
        return apiService.getAvailableSources(apiKey).toDomain()
    }
}