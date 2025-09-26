package com.example.globalnews.utils

import okio.IOException
import retrofit2.HttpException

fun handleError(e: Exception) {
    when (e) {
        is IOException -> "Please check your internet connection."
        is HttpException -> "Server error: ${e.code()}"
        else -> e.message ?: "An unknown error occurred."
    }
}