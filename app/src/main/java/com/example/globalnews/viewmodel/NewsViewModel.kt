package com.example.globalnews.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.globalnews.domain.model.Article
import com.example.globalnews.domain.usecase.NewsUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(private val newsUseCases: NewsUseCases) : ViewModel() {

    private val _topHeadlines = MutableLiveData<List<Article>>()
    val topHeadlines: LiveData<List<Article>> get() = _topHeadlines

    private val _allNews = MutableLiveData<List<Article>>()
    val allNews: LiveData<List<Article>> get() = _allNews

    private val _availableSources = MutableLiveData<List<Article>>()
    val availableSources: LiveData<List<Article>> get() = _availableSources

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> get() = _error

    init {
        getTopHeadlines("us")
        getAllNews("everything")
        getAvailableSources()
    }

    fun getTopHeadlines(country: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val articles = newsUseCases.getTopHeadlines(country)
                _topHeadlines.value = articles
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _loading.value = false
            }
        }
    }

    private fun getAllNews(query: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val articles = newsUseCases.getNews(query)
                _allNews.value = articles
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _loading.value = false
            }
        }
    }

    private fun getAvailableSources() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val articles = newsUseCases.getSources()
                _availableSources.value = articles
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message ?: "Unknown error"
            } finally {
                _loading.value = false
            }
        }
    }
}