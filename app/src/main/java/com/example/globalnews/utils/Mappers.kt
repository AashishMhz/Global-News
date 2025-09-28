package com.example.globalnews.utils

import com.example.globalnews.data.remote.dto.ArticleDto
import com.example.globalnews.data.remote.dto.NewsResponseDto
import com.example.globalnews.data.remote.dto.SourceDto
import com.example.globalnews.domain.model.Article
import com.example.globalnews.domain.model.NewsResponse
import com.example.globalnews.domain.model.Source

fun ArticleDto.toDomain(): Article = Article(
    source = source?.toDomain() ?: Source("", ""),
    author = author.orEmpty(),
    content = content.orEmpty(),
    description = description.orEmpty(),
    publishedAt = publishedAt.orEmpty(),
    title = title.orEmpty(),
    url = url.orEmpty(),
    urlToImage = urlToImage.orEmpty()
)

fun SourceDto.toDomain(): Source = Source(
    id = id.orEmpty(),
    name = name.orEmpty()
)

fun NewsResponseDto.toDomain(): NewsResponse = NewsResponse(
    articles = articles?.map { it.toDomain() } ?: emptyList(),
    status = status.orEmpty(),
    totalResults = totalResults ?: 0
)