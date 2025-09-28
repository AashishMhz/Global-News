package com.example.globalnews.data.remote.dto

data class NewsResponseDto(
    val articles: List<ArticleDto>?,
    val status: String?,
    val totalResults: Int?
)