package com.example.globalnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globalnews.data.model.Article
import com.example.globalnews.data.network.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _topHeadlines = MutableLiveData<List<Article>>()
    val topHeadlines: LiveData<List<Article>> get() = _topHeadlines

    private val _allNews = MutableLiveData<List<Article>>()
    val allNews: LiveData<List<Article>> get() = _allNews

    private val _availableSources = MutableLiveData<List<Article>>()
    val availableSources: LiveData<List<Article>> get() = _availableSources

    private val apikey = "a13c0be8ab5e43e082a02d3e0d449785"
    private val source = "bbc-news"

    init {
        getTopHeadlines()
        getAllNews("everything")
        getAvailableSources()
    }

    private fun getTopHeadlines() {
        viewModelScope.launch {
            try {
                val response = newsRepository.getTopHeadlines("us", apikey)
                _topHeadlines.value = response.articles
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getAllNews(query: String) {
        viewModelScope.launch {
            try {
                val response = newsRepository.getNews(query, apikey)
                _allNews.value = response.articles
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getAvailableSources() {
        viewModelScope.launch {
            try {
                val response = newsRepository.getAvailableSources(apikey)
                _availableSources.value = response.articles
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}