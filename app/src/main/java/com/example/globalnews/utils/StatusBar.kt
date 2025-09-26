package com.example.globalnews.utils

import android.app.Activity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.globalnews.R

fun Activity.setCustomStatusBar() {
    window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar_color)
    WindowCompat.setDecorFitsSystemWindows(window, true)
    val controller = WindowInsetsControllerCompat(window, window.decorView)
    controller.isAppearanceLightStatusBars = false
}