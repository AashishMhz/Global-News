package com.example.globalnews.domain.usecase

import com.example.globalnews.domain.model.Article
import com.example.globalnews.domain.repository.NewsRepository

class NewsUseCases(private val repository: NewsRepository) {

    suspend fun getTopHeadlines(country: String): List<Article> {
        val response = repository.getTopHeadlines(country)
        return response.articles
    }

    suspend fun getNews(query: String): List<Article> {
        val response = repository.getNews(query)
        return response.articles
    }

    suspend fun getSources(): List<Article> {
        val response = repository.getAvailableSources()
        return response.articles
    }
}