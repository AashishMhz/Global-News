package com.example.globalnews.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

fun formatWithDateUtils(publishedAt: String): String {
    return try {
        // NewsAPI format: "2025-09-24T14:30:00Z"
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")

        val time = sdf.parse(publishedAt)?.time ?: return ""
        DateUtils.getRelativeTimeSpanString(
            time,                         // when the article was published
            System.currentTimeMillis(),   // current time
            DateUtils.MINUTE_IN_MILLIS    // resolution
        ).toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}